package com.ruansu.littletest.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.ruansu.littletest.AddressManager;
import com.ruansu.littletest.Constant;
import com.ruansu.littletest.R;
import com.ruansu.littletest.adapter.AddressAdapter;
import com.ruansu.littletest.bean.AddressBean;
import com.ruansu.littletest.bean.AddressJsonBean;
import com.ruansu.littletest.bean.AddressResult;
import com.ruansu.littletest.databinding.FragmentAddressBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends BaseFragment {

    private FragmentAddressBinding binding;

    public AddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_address_to_edit);
            }
        });
    }

    private List<AddressBean> getFromAsserts() {
        String data = ResourceUtils.readAssets2String("address_data.json");
        if (!TextUtils.isEmpty(data)) {
            AddressJsonBean addressJsonBean = GsonUtils.fromJson(data, AddressJsonBean.class);
            if (addressJsonBean != null) {
                List<AddressResult> addressResultList = addressJsonBean.getResult();
                if (!isListEmpty(addressResultList)) {
                    List<AddressBean> addressBeanList = new ArrayList<>();
                    for (AddressResult result : addressResultList) {
                        AddressBean bean = new AddressBean();
                        bean.setName(result.getConsignee());
                        bean.setPhone(result.getMobile());
                        bean.setDefault(result.isIsdefault());
                        bean.setStreet(result.getAddress());
                        bean.setAddress(result.getCountryName() + result.getProvinceName()
                                + result.getCityName() + result.getDistrictName());
                        addressBeanList.add(bean);
                    }
                    return addressBeanList;
                }
            }
        }
        return null;
    }

    private void save(List<AddressBean> addressBeanList) {
        for (AddressBean bean : addressBeanList) {
            AddressManager.saveOrUpdate(bean);
        }
    }

    private boolean isAddressRead() {
        return SPUtils.getInstance().getBoolean(Constant.IS_ADDRESS_READ, false);
    }

    private void setAddressRead() {
        SPUtils.getInstance().put(Constant.IS_ADDRESS_READ, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<AddressBean> addressBeanList = AddressManager.getAddress();
        if (isListEmpty(addressBeanList) && !isAddressRead()) {
            setAddressRead();
            addressBeanList = getFromAsserts();
            if (!isListEmpty(addressBeanList)) save(addressBeanList);
        }
        if (!isListEmpty(addressBeanList)) {
            int size = addressBeanList.size();
            if (size == 1) {
                AddressBean addressBean = addressBeanList.get(0);
                addressBean.setDefault(true);
                AddressManager.saveOrUpdate(addressBean);
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getContext(), LinearLayoutManager.VERTICAL, false);
            binding.addressRecycler.setLayoutManager(linearLayoutManager);
            binding.addressRecycler.setAdapter(new AddressAdapter(addressBeanList));
            binding.addressRecycler.addItemDecoration(new SpaceItemDecoration());
        } else {
            binding.addressRecycler.setAdapter(null);
        }
    }

    static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.top = SizeUtils.dp2px(10);
        }
    }

}
