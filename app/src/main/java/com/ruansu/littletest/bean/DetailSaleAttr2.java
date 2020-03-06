package com.ruansu.littletest.bean;

public class DetailSaleAttr2 {

    private int stockNum;
    private boolean isSelected, isEnable = true;
    private String saleAttr2Key, saleAttr2ValueCode, saleAttr2Value, barcodeSysCode;

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

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getSaleAttr2Key() {
        return saleAttr2Key;
    }

    public void setSaleAttr2Key(String saleAttr2Key) {
        this.saleAttr2Key = saleAttr2Key;
    }

    public String getSaleAttr2ValueCode() {
        return saleAttr2ValueCode;
    }

    public void setSaleAttr2ValueCode(String saleAttr2ValueCode) {
        this.saleAttr2ValueCode = saleAttr2ValueCode;
    }

    public String getSaleAttr2Value() {
        return saleAttr2Value;
    }

    public void setSaleAttr2Value(String saleAttr2Value) {
        this.saleAttr2Value = saleAttr2Value;
    }

    public String getBarcodeSysCode() {
        return barcodeSysCode;
    }

    public void setBarcodeSysCode(String barcodeSysCode) {
        this.barcodeSysCode = barcodeSysCode;
    }
}
