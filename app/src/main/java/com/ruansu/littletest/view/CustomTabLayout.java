package com.ruansu.littletest.view;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.tabs.TabLayout;

public class CustomTabLayout extends TabLayout {

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(String[] titles) {
        for (int i = 0; i < getTabCount(); i++) {
            Tab tab = getTabAt(i);
            if (tab != null) tab.setText(titles[i]);
        }
    }

}
