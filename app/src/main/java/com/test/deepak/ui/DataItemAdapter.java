package com.test.deepak.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.test.deepak.R;
import com.test.deepak.data.local.entity.ShadiModel;
import com.test.deepak.databinding.DataitemCellBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {


    private final Context mContext;
    private List<ShadiModel> mDataItemList;
    private OnItemClickListener onItemClickListener;
    private int currentPos = -1;

    public DataItemAdapter(Context mContext, List<ShadiModel> mDataItemList) {
        this.mDataItemList = mDataItemList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final DataitemCellBinding binding = DataBindingUtil.inflate(inflater, R.layout.dataitem_cell, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ShadiModel mDataItem = this.mDataItemList.get(position);
        holder.bind(mDataItem);

        Glide.with(mContext).load(mDataItem.getImage()).into(holder.binding.ivimage);

        if (position == currentPos) {
            // selected true
        } else {
            // selected false
        }

        if (mDataItem.getStatus().equals("")) {
            holder.binding.layoutlikedislike.setVisibility(View.VISIBLE);
            holder.binding.status.setVisibility(View.GONE);
        } else {
            holder.binding.layoutlikedislike.setVisibility(View.GONE);
            holder.binding.status.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mDataItemList.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public List<ShadiModel> getDataItem() {
        return mDataItemList;
    }

    public void addChatMassgeModel(ShadiModel mDataItem) {
        try {
            this.mDataItemList.add(mDataItem);
            notifyItemInserted(getItemCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataItemList(List<ShadiModel> mDataItemList) {
        this.mDataItemList = mDataItemList;
        notifyDataSetChanged();
    }

    private void dataSetChanged() {
        this.notifyDataSetChanged();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final DataitemCellBinding binding;

        public ViewHolder(final View view, final DataitemCellBinding binding) {
            super(view);
            this.binding = binding;
            view.setOnClickListener(this);
            binding.acceptBtn.setOnClickListener(this::onClick);
            binding.rejectBtn.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            try {
                if (onItemClickListener != null) {
                    setCurrentPos(getAdapterPosition());
                    notifyItemRangeChanged(0, mDataItemList.size());
                    onItemClickListener.onItemClick(v, getAdapterPosition());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @UiThread
        public void bind(final ShadiModel mDataItem) {
            this.binding.setShadiModel(mDataItem);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void deleteitem(int position) {
        mDataItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataItemList.size());
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public void clearAdapter() {
        int size = mDataItemList.size();
        mDataItemList.clear();
        notifyItemRangeRemoved(0, size);
    }


}