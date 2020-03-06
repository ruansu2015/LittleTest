package com.ruansu.littletest.bean;

import java.util.List;

public class AddressJsonBean {

    private boolean ok;
    private String status;
    private List<AddressResult> result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AddressResult> getResult() {
        return result;
    }

    public void setResult(List<AddressResult> result) {
        this.result = result;
    }
}
