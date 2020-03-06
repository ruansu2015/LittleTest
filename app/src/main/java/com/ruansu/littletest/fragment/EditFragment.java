package com.ruansu.littletest.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ruansu.littletest.AddressManager;
import com.ruansu.littletest.Constant;
import com.ruansu.littletest.R;
import com.ruansu.littletest.bean.AddressBean;
import com.ruansu.littletest.bean.JsonBean;
import com.ruansu.littletest.databinding.FragmentEditBinding;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class EditFragment extends BaseFragment implements View.OnClickListener {

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private AddressBean addressBean;
    private FragmentEditBinding binding;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = setDataBindingView(inflater, container, R.layout.fragment_edit);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setListener(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            addressBean = (AddressBean) bundle.getSerializable(Constant.SERIALIZABLE);
            if (addressBean != null) initView();
        }
        String data = ResourceUtils.readAssets2String("province.json");
        if (!TextUtils.isEmpty(data)) initData(data);
    }

    private void initView() {
        binding.receiver.setEditContent(addressBean.getName());
        binding.phone.setEditContent(addressBean.getPhone());
        binding.address.setEditContent(addressBean.getAddress());
        binding.street.setEditContent(addressBean.getStreet());
    }

    private void initData(String data) {
        options1Items = parseData(data);
        for (int i = 0; i < options1Items.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < options1Items.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = options1Items.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                //该城市的所有地区列表
                ArrayList<String> city_AreaList = new ArrayList<>(options1Items.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            options2Items.add(cityList);
            options3Items.add(province_AreaList);
        }
    }

    private ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = GsonUtils.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    private void showPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                binding.address.setEditContent(tx);
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private boolean save() {
        String name = binding.receiver.getText();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShort(getString(R.string.input_name));
            return false;
        }
        String phone = binding.phone.getText();
        if (TextUtils.isEmpty(phone) || !RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort(getString(R.string.input_phone_number));
            return false;
        }
        String address = binding.address.getText();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showShort(getString(R.string.input_area));
            return false;
        }
        String street = binding.street.getText();
        if (TextUtils.isEmpty(street)) {
            ToastUtils.showShort(getString(R.string.street_info));
            return false;
        }
        if (addressBean == null)
            addressBean = new AddressBean();
        addressBean.setName(name);
        addressBean.setPhone(phone);
        addressBean.setAddress(address);
        addressBean.setStreet(street);
        AddressManager.saveOrUpdate(addressBean);
        return true;
    }

    private void closeSoftInput(View v) {
        InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null)
            manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        closeSoftInput(v);
        switch (v.getId()) {
            case R.id.address:
                showPickerView();
                break;
            case R.id.save:
                if (save()) Navigation.findNavController(v).navigateUp();
                break;
        }
    }
}
