package com.ruansu.littletest.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ruansu.littletest.Constant;
import com.ruansu.littletest.R;
import com.ruansu.littletest.adapter.FragmentViewPagerAdapter;
import com.ruansu.littletest.adapter.GalleryAdapter;
import com.ruansu.littletest.bean.DetailBean;
import com.ruansu.littletest.bean.DetailGallery;
import com.ruansu.littletest.bean.DetailResult;
import com.ruansu.littletest.databinding.FragmentDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends BaseFragment {

    private FragmentDetailBinding binding;

    private DetailBean detailBean;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = ResourceUtils.readAssets2String("detail_data.json");
        if (!TextUtils.isEmpty(data)) {
            detailBean = GsonUtils.fromJson(data, DetailBean.class);
            if (detailBean != null) initViews();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.convenientBanner.startTurning(3000L);
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.convenientBanner.stopTurning();
    }

    private void initViews() {
        final DetailResult result = detailBean.getResult();
        if (result != null) {
            if (NetworkUtils.isConnected()) {
                initGallery(result);
            } else
                ToastUtils.showLong("无网络！");
            binding.price.setText(String.format("%s%s", getString(R.string.rmb), result.getSalePrice()));
            binding.listingPrice.setText(String.format("%s%s", getString(R.string.market_price), result.getMarketPrice()));
            initTabViewPager();
            binding.chooseDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.SERIALIZABLE, result);
                    Navigation.findNavController(v).navigate(R.id.action_detail_to_choose, bundle);
                }
            });
        }
    }

    private void initGallery(DetailResult result) {
        binding.convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new GalleryAdapter(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.gallery_item_layout;
            }
        }, getGalleryList(result.getGalleryList()))
                .setPageIndicator(new int[]{R.drawable.indicator_normal, R.drawable.indicator_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    private List<DetailGallery> getGalleryList(List<DetailGallery> galleries) {
        if (galleries != null && !galleries.isEmpty()) {
            for (DetailGallery gallery : galleries)
                gallery.setImageUrl(Constant.DOMAIN + gallery.getImageUrl());
            return galleries;
        }
        return null;
    }

    private void initTabViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new EmptyFragment(getString(R.string.details)));
        fragments.add(new EmptyFragment(getString(R.string.comment)));
        binding.viewPager.setAdapter(new FragmentViewPagerAdapter(getChildFragmentManager(), fragments));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.tabLayout.setData(new String[]{getString(R.string.details), getString(R.string.comment)});
    }

}
