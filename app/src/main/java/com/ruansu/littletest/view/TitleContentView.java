package com.ruansu.littletest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ruansu.littletest.R;
import com.ruansu.littletest.databinding.TitleContentLayoutBinding;

public class TitleContentView extends ConstraintLayout {

    private TitleContentLayoutBinding binding;

    public TitleContentView(Context context) {
        super(context);
        init();
    }

    public TitleContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setViewsFromAttrs(attrs);
    }

    public TitleContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setViewsFromAttrs(attrs);
    }

    private void init() {
        binding = TitleContentLayoutBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    @SuppressLint("CustomViewStyleable")
    public void setViewsFromAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.view);
        String title = typedArray.getString(R.styleable.view_title);
        if (!TextUtils.isEmpty(title)) setTitle(title);
        String hint = typedArray.getString(R.styleable.view_hint);
        if (!TextUtils.isEmpty(hint)) setHint(hint);
        binding.editText.setFocusable(typedArray.getBoolean(R.styleable.view_enabled, true));
        if (typedArray.getBoolean(R.styleable.view_isPhone, false)) {
            binding.editText.setInputType(InputType.TYPE_CLASS_PHONE);
            binding.editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        }
        typedArray.recycle();
    }

    public void setTitle(String text) {
        binding.title.setText(text);
    }

    public void setHint(String text) {
        binding.editText.setHint(text);
    }

    public void setEditContent(String text) {
        binding.editText.setText(text);
    }

    public String getText() {
        return binding.editText.getText().toString();
    }

}
