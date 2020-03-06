package com.ruansu.littletest.bean;

public class DetailSaleAttr1 {

    private int stockNum;
    private boolean isSelected;
    private String imageUrl, saleAttr1Key, saleAttr1Value, saleAttr1ValueCode, barcodeSysCode;

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSaleAttr1Key() {
        return saleAttr1Key;
    }

    public void setSaleAttr1Key(String saleAttr1Key) {
        this.saleAttr1Key = saleAttr1Key;
    }

    public String getSaleAttr1Value() {
        return saleAttr1Value;
    }

    public void setSaleAttr1Value(String saleAttr1Value) {
        this.saleAttr1Value = saleAttr1Value;
    }

    public String getSaleAttr1ValueCode() {
        return saleAttr1ValueCode;
    }

    public void setSaleAttr1ValueCode(String saleAttr1ValueCode) {
        this.saleAttr1ValueCode = saleAttr1ValueCode;
    }

    public String getBarcodeSysCode() {
        return barcodeSysCode;
    }

    public void setBarcodeSysCode(String barcodeSysCode) {
        this.barcodeSysCode = barcodeSysCode;
    }
}
