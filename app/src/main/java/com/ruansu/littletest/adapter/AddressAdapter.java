package com.ruansu.littletest.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ruansu.littletest.AddressManager;
import com.ruansu.littletest.Constant;
import com.ruansu.littletest.R;
import com.ruansu.littletest.bean.AddressBean;
import com.ruansu.littletest.databinding.AddressItemLayoutBinding;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<AddressBean> addressBeanList;

    public AddressAdapter(List<AddressBean> addressBeanList) {
        this.addressBeanList = addressBeanList;
    }

    private void setDefault(int position) {
        for (int i = 0; i < addressBeanList.size(); i++) {
            AddressBean addressBean = addressBeanList.get(i);
            if (position == i)
                addressBean.setDefault(true);
            else {
                addressBean.setDefault(false);
                addressBean.setToDefault("isDefault");
            }
            AddressManager.saveOrUpdate(addressBean);
        }
        notifyDataSetChanged();
    }

    private void delete(AddressBean bean) {
        if (bean != null) {
            notifyItemRemoved(getPosition(bean));
            AddressManager.delete(bean);
            addressBeanList.remove(bean);
            if (bean.isDefault()) resetDefault();
        }
    }

    private int getPosition(AddressBean bean) {
        for (int i = 0; i < addressBeanList.size(); i++) {
            AddressBean addressBean = addressBeanList.get(i);
            if (addressBean.getId() == bean.getId()) return i;
        }
        return 0;
    }

    private void resetDefault() {
        int size = addressBeanList.size();
        if (size > 0) {
            AddressBean bean = addressBeanList.get(0);
            bean.setDefault(true);
            AddressManager.saveOrUpdate(bean);
            notifyItemChanged(0);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((AddressItemLayoutBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.address_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position, addressBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return addressBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int position;
        private AddressBean bean;
        private AddressItemLayoutBinding binding;

        ViewHolder(@NonNull AddressItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(int position, AddressBean bean) {
            this.bean = bean;
            this.position = position;
            binding.setListener(this);
            binding.name.setText(bean.getName());
            binding.phone.setText(bean.getPhone());
            binding.defaultAddress.setBackgroundResource(bean.isDefault() ?
                    R.drawable.default_shape : R.drawable.size_normal_shape);
            binding.address.setText(String.format("%s%s", bean.getAddress(), bean.getStreet()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.defaultAddress:
                    setDefault(position);
                    break;
                case R.id.edit:
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.SERIALIZABLE, bean);
                    Navigation.findNavController(v).navigate(R.id.action_address_to_edit, bundle);
                    break;
                case R.id.delete:
                    delete(bean);
                    break;
            }
        }
    }

}
