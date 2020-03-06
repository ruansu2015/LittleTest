package com.ruansu.littletest.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.ruansu.littletest.Constant;
import com.ruansu.littletest.R;
import com.ruansu.littletest.adapter.ColorAdapter;
import com.ruansu.littletest.adapter.SizeAdapter;
import com.ruansu.littletest.bean.DetailResult;
import com.ruansu.littletest.bean.DetailSaleAttr;
import com.ruansu.littletest.bean.DetailSaleAttr1;
import com.ruansu.littletest.bean.DetailSaleAttr2;
import com.ruansu.littletest.bean.DetailSkuInfo;
import com.ruansu.littletest.callback.OnColorItemSelectedCallback;
import com.ruansu.littletest.callback.OnSizeItemSelectedCallback;
import com.ruansu.littletest.databinding.FragmentChooseBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseFragment extends BaseFragment implements View.OnClickListener, OnColorItemSelectedCallback, OnSizeItemSelectedCallback {

    private DetailResult result;
    private FragmentChooseBinding binding;

    private List<DetailSkuInfo> skuInfoList;
    private List<DetailSaleAttr1> colorList;
    private List<DetailSaleAttr2> sizeList;

    private DetailSaleAttr1 selectedColor;
    private DetailSaleAttr2 selectedSize;

    private int stockNum;

    private SizeAdapter sizeAdapter;

    public ChooseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = setDataBindingView(inflater, container, R.layout.fragment_choose);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setListener(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            result = (DetailResult) bundle.getSerializable(Constant.SERIALIZABLE);
            if (result != null) {
                initData();
                initViews();
            }
        }
    }

    private void initData() {
        skuInfoList = result.getSkuInfo();
        DetailSaleAttr detailSaleAttr = result.getSaleAttrList();
        if (detailSaleAttr != null) {
            colorList = detailSaleAttr.getSaleAttr1List();
            sizeList = detailSaleAttr.getSaleAttr2List();
        }
    }

    private void initViews() {
        Glide.with(getContext()).load(Constant.DOMAIN + result.getProductUrl()).into(binding.image);
        binding.price.setText(String.format("%s%s", getString(R.string.rmb), result.getSalePrice()));
        initColor();
        initSize();
    }

    private void initColor() {
        if (isListEmpty(colorList)) return;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.colorRecycler.setLayoutManager(linearLayoutManager);
        ColorAdapter adapter = new ColorAdapter(getColorList());
        adapter.setOnColorSelectedListener(this);
        binding.colorRecycler.setAdapter(adapter);
        binding.colorRecycler.addItemDecoration(new SpaceItemDecoration(false));
    }

    private List<DetailSaleAttr1> getColorList() {
        for (DetailSaleAttr1 detailSaleAttr1 : colorList)
            detailSaleAttr1.setImageUrl(Constant.DOMAIN + detailSaleAttr1.getImageUrl());
        return colorList;
    }

    private void initSize() {
        if (isListEmpty(sizeList)) return;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        binding.sizeRecycler.setLayoutManager(layoutManager);
        sizeAdapter = new SizeAdapter(sizeList);
        sizeAdapter.setOnSizeItemSelectedCallback(this);
        binding.sizeRecycler.setAdapter(sizeAdapter);
        binding.sizeRecycler.addItemDecoration(new SpaceItemDecoration(true));
    }

    private int getEditNum() {
        return Integer.parseInt(binding.editText.getText().toString());
    }

    private boolean isColorSizeChooser() {
        if (selectedColor == null) {
            ToastUtils.showShort("请选择颜色");
            return false;
        }
        if (selectedSize == null) {
            ToastUtils.showShort("请选择尺码");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int number;
        switch (v.getId()) {
            case R.id.close:
                Navigation.findNavController(v).popBackStack();
                break;
            case R.id.minus:
                if (isColorSizeChooser()) {
                    number = getEditNum();
                    number--;
                    matchNum(number);
                }
                break;
            case R.id.add:
                if (isColorSizeChooser()) {
                    number = getEditNum();
                    number++;
                    matchNum(number);
                }
                break;
            case R.id.ok:
                if (isColorSizeChooser())
                    Navigation.findNavController(v).navigateUp();
                break;
        }
    }

    private List<DetailSkuInfo> getColorSku(DetailSaleAttr1 data) {
        if (isListEmpty(skuInfoList)) return null;
        List<DetailSkuInfo> colorSkuList = new ArrayList<>();
        for (DetailSkuInfo info : skuInfoList) {
            if (info.getSaleAttr1ValueCode().equalsIgnoreCase(data.getSaleAttr1ValueCode()))
                colorSkuList.add(info);
        }
        return colorSkuList;
    }

    private void reSetSizeList(List<DetailSkuInfo> detailSkuInfoList) {
        if (isListEmpty(detailSkuInfoList)) {
            for (DetailSaleAttr2 detailSaleAttr2 : sizeList)
                detailSaleAttr2.setEnable(false);
        } else {
            for (DetailSaleAttr2 detailSaleAttr2 : sizeList) {
                boolean isExit = false;
                int num = 0;
                for (DetailSkuInfo detailSkuInfo : detailSkuInfoList) {
                    if (detailSkuInfo.getSaleAttr2ValueCode().equalsIgnoreCase(detailSaleAttr2.getSaleAttr2ValueCode())) {
                        isExit = true;
                        num = detailSkuInfo.getStockNum();
                        break;
                    }
                }
                detailSaleAttr2.setEnable(isExit && num > 0);
                detailSaleAttr2.setSelected(false);
            }
        }
        sizeAdapter.setDetailSaleAttr2List(sizeList);
    }

    @Override
    public void onItemSelected(DetailSaleAttr1 data) {
        if (selectedColor != data) {
            selectedColor = data;
            selectedSize = null;
            reSetSizeList(getColorSku(data));
        }
    }

    private int getStockNum(DetailSaleAttr1 detailSaleAttr1, DetailSaleAttr2 data) {
        if (!isListEmpty(skuInfoList) && detailSaleAttr1 != null && data != null) {
            for (DetailSkuInfo info : skuInfoList) {
                if (info.getSaleAttr1ValueCode().equalsIgnoreCase(detailSaleAttr1.getSaleAttr1ValueCode())
                        && info.getSaleAttr2ValueCode().equalsIgnoreCase(data.getSaleAttr2ValueCode()))
                    return info.getStockNum();
            }
        }
        return 0;
    }

    @Override
    public void onItemSelected(DetailSaleAttr2 data) {
        selectedSize = data;
        stockNum = getStockNum(selectedColor, data);
        matchNum(getEditNum());
    }

    private void matchNum(int number) {
        if (number > stockNum)
            binding.editText.setText(String.valueOf(stockNum));
        else if (number < 1)
            binding.editText.setText(String.valueOf(1));
        else
            binding.editText.setText(String.valueOf(number));
    }

    static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private boolean isGrid;

        SpaceItemDecoration(boolean isGrid) {
            this.isGrid = isGrid;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int space = SizeUtils.dp2px(10);
            outRect.left = space;
            if (isGrid) {
                outRect.bottom = space;
            }
        }
    }

}
