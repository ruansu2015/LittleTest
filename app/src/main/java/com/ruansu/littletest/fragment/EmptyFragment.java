package com.ruansu.littletest.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ruansu.littletest.R;
import com.ruansu.littletest.adapter.ItemAdapter;
import com.ruansu.littletest.bean.ItemBean;
import com.ruansu.littletest.databinding.FragmentEmptyBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmptyFragment extends BaseFragment {

    private String title;
    private FragmentEmptyBinding binding;

    public EmptyFragment(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEmptyBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager;
        if (title.equalsIgnoreCase(getString(R.string.details)))
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        else
            layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(new ItemAdapter(getList(!title.equalsIgnoreCase(getString(R.string.details)))));
    }

    private List<ItemBean> getList(boolean staggered) {
        List<ItemBean> itemBeans = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ItemBean bean = new ItemBean();
            if (staggered) {
                StringBuilder builder = new StringBuilder();
                builder.append(title);
                Random random = new Random();
                if (random.nextBoolean())
                    builder.append("\n");
                builder.append(i);
                bean.setTitle(builder.toString());
            } else
                bean.setTitle(title + i);
            bean.setBgColor(randomColor());
            itemBeans.add(bean);
        }
        return itemBeans;
    }

    private int randomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

}
