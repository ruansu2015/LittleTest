package com.ruansu.littletest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ruansu.littletest.R;
import com.ruansu.littletest.bean.DetailSaleAttr1;
import com.ruansu.littletest.callback.OnColorItemSelectedCallback;
import com.ruansu.littletest.databinding.ColorItemLayoutBinding;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private List<DetailSaleAttr1> detailSaleAttr1List;

    private OnColorItemSelectedCallback callback;

    public ColorAdapter(List<DetailSaleAttr1> detailSaleAttr1List) {
        this.detailSaleAttr1List = detailSaleAttr1List;
    }

    private void refreshData(int position) {
        for (int i = 0; i < detailSaleAttr1List.size(); i++) {
            DetailSaleAttr1 detailSaleAttr1 = detailSaleAttr1List.get(i);
            detailSaleAttr1.setSelected(position == i);
        }
        notifyDataSetChanged();
    }

    public void setOnColorSelectedListener(OnColorItemSelectedCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ColorItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position, detailSaleAttr1List.get(position));
    }

    @Override
    public int getItemCount() {
        return detailSaleAttr1List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int position;
        private DetailSaleAttr1 data;
        private ColorItemLayoutBinding binding;

        ViewHolder(@NonNull ColorItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(int position, DetailSaleAttr1 data) {
            this.data = data;
            this.position = position;
            Glide.with(binding.getRoot()).load(data.getImageUrl()).into(binding.image);
            if (data.isSelected())
                binding.layout.setBackgroundResource(R.drawable.color_selected_shape);
            else
                binding.layout.setBackground(null);
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            refreshData(position);
            if (callback != null) callback.onItemSelected(data);
        }
    }

}
