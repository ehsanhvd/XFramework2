package com.hvd.xview.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class PaginationAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    private boolean loading = false; //when you are fetching data (db or webservice), you must set this flag true to prevent multiple api calls, after response,revert it to false
    private boolean listFinished = false; //when true, endlist callback wont call
    private EndListReachedListener listReachedListener;

    public PaginationAdapter() {
    }

    public PaginationAdapter(EndListReachedListener listReachedListener) {
        this.listReachedListener = listReachedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        if (position == getItemCount() - 1 &&
                !listFinished &&
                !loading &&
                listReachedListener != null) {

            loading = true;
            listReachedListener.endListReached(getItemCount());
        }
    }

    public void setListFinished(){
        listFinished = true;
    }

    public boolean isListFinished() {
        return listFinished;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setEndListListener(EndListReachedListener listReachedListener) {
        this.listReachedListener = listReachedListener;
    }

    public interface EndListReachedListener {
        void endListReached(int count);
    }
}