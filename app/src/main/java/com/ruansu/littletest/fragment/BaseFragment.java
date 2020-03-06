package com.ruansu.littletest.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    <T extends ViewDataBinding> T setDataBindingView(LayoutInflater inflater, ViewGroup container, int layoutResID) {
        return DataBindingUtil.inflate(inflater, layoutResID, container, false);
    }

    boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }

}
