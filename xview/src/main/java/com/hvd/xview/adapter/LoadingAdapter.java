package com.hvd.xview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hvd.xview.R;

public abstract class LoadingAdapter<T extends RecyclerView.ViewHolder> extends PaginationAdapter<RecyclerView.ViewHolder> {
    private static int VIEWTYPE_LOADING = 1;
    private static int VIEWTYPE_CONTENT = 2;
    private RecyclerView recyclerView;

    public LoadingAdapter(RecyclerView recyclerView, EndListReachedListener listReachedListener) {
        super(listReachedListener);
        this.recyclerView = recyclerView;
    }

    public LoadingAdapter(RecyclerView recyclerView) {
        super();
        this.recyclerView = recyclerView;
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) == VIEWTYPE_CONTENT) {
            onContentBindViewHolder((T) holder, position);
        }
    }

    protected abstract void onContentBindViewHolder(@NonNull T holder, int position);

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEWTYPE_LOADING) {
            return new LoadingViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.row_loading, parent, false)
            );
        } else {
            return onCreateContentViewHolder(parent, viewType);
        }
    }

    protected abstract RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemViewType(int position) {
        if (isLoading() && position == getItemCount() - 1 && !isListFinished()) {
            return VIEWTYPE_LOADING;
        } else {
            return VIEWTYPE_CONTENT;
        }
    }

    @Override
    public final int getItemCount() {
        if (isLoading()){
            return getContentItemCount() + 1;
        } else {
            return getContentItemCount();
        }
    }

    public abstract int getContentItemCount();

    @Override
    public void setLoading(final boolean loading) {
        super.setLoading(loading);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
