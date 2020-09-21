package com.hvd.xview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneralAdapter<T, V extends GeneralAdapter.BaseHolder> extends LoadingAdapter<V> {

    private List<T> items = new ArrayList<>();

    public GeneralAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public void addItems(List<T> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void addItem(T newItems) {
        items.add(newItems);
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    protected void onContentBindViewHolder(@NonNull V holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(parent.getContext()), parent, viewType);
    }

    @Override
    public int getContentItemCount() {
        return items.size();
    }

    protected abstract V getViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    public static abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

        private T item;
        private Activity activity;

        public BaseHolder(Activity activity, @NonNull View itemView) {
            super(itemView);
            this.activity = activity;
            itemView.setOnClickListener(this);
        }

        public BaseHolder(Activity activity, @LayoutRes int layout, ViewGroup parent) {
            super(LayoutInflater.from(activity).inflate(layout, parent, false));
            this.activity = activity;
            itemView.setOnClickListener(this);
        }

        public T getItem() {
            return item;
        }

        public Activity getActivity() {
            return activity;
        }

        public void bind(T item){
            this.item = item;
            onBind(activity, item);
        }

        protected abstract void onBind(Activity activity, T item);

        @Override
        public void onClick(View v) {

        }
    }
}
