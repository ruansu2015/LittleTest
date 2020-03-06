package com.ruansu.littletest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.ruansu.littletest.R;
import com.ruansu.littletest.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = setDataBindingView(inflater, container, R.layout.fragment_main);
        binding.setListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(v.getId() == R.id.detail ?
                R.id.action_main_to_detail : R.id.action_main_to_address);
    }

}
