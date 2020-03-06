package com.ruansu.littletest.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.ruansu.littletest.R;
import com.ruansu.littletest.bean.DetailGallery;

public class GalleryAdapter extends Holder<DetailGallery> {

    private ImageView imageView;

    public GalleryAdapter(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView = itemView.findViewById(R.id.image);
    }

    @Override
    public void updateUI(DetailGallery data) {
        Glide.with(imageView).load(data.getImageUrl()).centerCrop().into(imageView);
    }

}
