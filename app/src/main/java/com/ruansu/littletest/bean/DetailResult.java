package com.ruansu.littletest.bean;

import java.io.Serializable;
import java.util.List;

public class DetailResult implements Serializable {

    private boolean isChange;
    private int status, commentLevel;
    private long categoryId, stockNum;
    private double marketPrice, protectPrice, salePrice;
    private List<DetailGallery> galleryList;
    private DetailSaleAttr saleAttrList;
    private List<DetailSkuInfo> skuInfo;
    private String[] categoryNameList;
    private String productSysCode, productName, productUrl, tagPosition, tagName, firstCategoryName, sizePicture, isPromotion;

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getStockNum() {
        return stockNum;
    }

    public void setStockNum(long stockNum) {
        this.stockNum = stockNum;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getProtectPrice() {
        return protectPrice;
    }

    public void setProtectPrice(double protectPrice) {
        this.protectPrice = protectPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public List<DetailGallery> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<DetailGallery> galleryList) {
        this.galleryList = galleryList;
    }

    public DetailSaleAttr getSaleAttrList() {
        return saleAttrList;
    }

    public void setSaleAttrList(DetailSaleAttr saleAttrList) {
        this.saleAttrList = saleAttrList;
    }

    public List<DetailSkuInfo> getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(List<DetailSkuInfo> skuInfo) {
        this.skuInfo = skuInfo;
    }

    public String[] getCategoryNameList() {
        return categoryNameList;
    }

    public void setCategoryNameList(String[] categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    public String getProductSysCode() {
        return productSysCode;
    }

    public void setProductSysCode(String productSysCode) {
        this.productSysCode = productSysCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getTagPosition() {
        return tagPosition;
    }

    public void setTagPosition(String tagPosition) {
        this.tagPosition = tagPosition;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public String getSizePicture() {
        return sizePicture;
    }

    public void setSizePicture(String sizePicture) {
        this.sizePicture = sizePicture;
    }

    public String getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion;
    }
}
