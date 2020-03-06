package com.ruansu.littletest.bean;

import java.util.List;

public class DetailSaleAttr {

    private List<DetailSaleAttr1> saleAttr1List;
    private List<DetailSaleAttr2> saleAttr2List;

    public List<DetailSaleAttr1> getSaleAttr1List() {
        return saleAttr1List;
    }

    public void setSaleAttr1List(List<DetailSaleAttr1> saleAttr1List) {
        this.saleAttr1List = saleAttr1List;
    }

    public List<DetailSaleAttr2> getSaleAttr2List() {
        return saleAttr2List;
    }

    public void setSaleAttr2List(List<DetailSaleAttr2> saleAttr2List) {
        this.saleAttr2List = saleAttr2List;
    }
}
