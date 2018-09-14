package com.sptechinfo.genericadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class GenericRecycleviewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> mData;
    private RecyclerView mRecyclerView;

    private OnClickEvent mOnClickEvent;

    public GenericRecycleviewAdapter(List<T> data) {
        mData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final RecyclerView.ViewHolder holder = onCreateViewHolderImpl(parent, this, viewType);

        if (mOnClickEvent != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickEvent.onClick(v, holder.getAdapterPosition());
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderImpl(holder, this, position);
    }

    @Override
    public int getItemCount() {
        return getItemCountImpl(this);
    }


    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }


    public abstract void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, GenericRecycleviewAdapter<T> adapter, int i);

    public abstract RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, GenericRecycleviewAdapter<T> adapter, int i);

    public abstract int getItemCountImpl(GenericRecycleviewAdapter<T> adapter);

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    public interface OnClickEvent {

        void onClick(View v, int position);
    }

    public void setOnClickEvent(OnClickEvent onClickEvent) {
        mOnClickEvent = onClickEvent;
    }

}
