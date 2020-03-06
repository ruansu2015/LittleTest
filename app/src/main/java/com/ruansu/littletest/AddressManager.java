package com.ruansu.littletest;

import com.ruansu.littletest.bean.AddressBean;

import org.litepal.LitePal;

import java.util.List;

public class AddressManager {

    public static List<AddressBean> getAddress() {
        return LitePal.findAll(AddressBean.class);
    }

    public static void saveOrUpdate(AddressBean bean) {
        if (bean.getId() > 0)
            bean.update(bean.getId());
        else
            bean.save();
    }

    public static void delete(AddressBean bean) {
        LitePal.delete(AddressBean.class, bean.getId());
    }

}
