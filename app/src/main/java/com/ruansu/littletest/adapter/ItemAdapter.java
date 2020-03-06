package com.ruansu.littletest.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruansu.littletest.bean.ItemBean;
import com.ruansu.littletest.databinding.ItemLayoutBinding;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ItemBean> itemBeans;

    public ItemAdapter(List<ItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(itemBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return itemBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutBinding binding;

        ViewHolder(@NonNull ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(ItemBean bean) {
            binding.text.setText(bean.getTitle());
            binding.text.setBackgroundColor(bean.getBgColor());
        }

    }

}
