package com.ruansu.littletest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruansu.littletest.R;
import com.ruansu.littletest.bean.DetailSaleAttr2;
import com.ruansu.littletest.callback.OnSizeItemSelectedCallback;
import com.ruansu.littletest.databinding.SizeItemLayoutBinding;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    private OnSizeItemSelectedCallback callback;
    private List<DetailSaleAttr2> detailSaleAttr2List;

    public SizeAdapter(List<DetailSaleAttr2> detailSaleAttr2List) {
        this.detailSaleAttr2List = detailSaleAttr2List;
    }

    public void setDetailSaleAttr2List(List<DetailSaleAttr2> detailSaleAttr2List) {
        this.detailSaleAttr2List = detailSaleAttr2List;
        notifyDataSetChanged();
    }

    private void refreshData(int position) {
        for (int i = 0; i < detailSaleAttr2List.size(); i++) {
            DetailSaleAttr2 detailSaleAttr2 = detailSaleAttr2List.get(i);
            detailSaleAttr2.setSelected(position == i);
        }
        notifyDataSetChanged();
    }

    public void setOnSizeItemSelectedCallback(OnSizeItemSelectedCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SizeItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position, detailSaleAttr2List.get(position));
    }

    @Override
    public int getItemCount() {
        return detailSaleAttr2List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int position;
        private DetailSaleAttr2 data;
        private SizeItemLayoutBinding binding;

        ViewHolder(@NonNull SizeItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setData(int position, DetailSaleAttr2 data) {
            this.data = data;
            this.position = position;
            binding.text.setText(data.getSaleAttr2Value());
            if (data.isEnable()) {
                binding.layout.setBackgroundResource(data.isSelected() ?
                        R.drawable.size_selected_shape : R.drawable.size_normal_shape);
                binding.getRoot().setOnClickListener(this);
            } else {
                binding.layout.setBackgroundResource(R.drawable.size_unenable_shape);
                binding.getRoot().setOnClickListener(null);
            }
        }

        @Override
        public void onClick(View v) {
            refreshData(position);
            if (callback != null) callback.onItemSelected(data);
        }
    }

}
