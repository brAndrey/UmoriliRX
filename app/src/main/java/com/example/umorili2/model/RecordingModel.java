package com.example.umorili2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RecordingModel {

    private String site;

    private String elementPureHtml;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getElementPureHtml() {
        return elementPureHtml;
    }

    public void setElementPureHtml(String elementPureHtml) {
        this.elementPureHtml = elementPureHtml;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n site:" + this.site);
        sb.append("\n elementPureHtml:" + this.elementPureHtml);
        sb.append("\n *****************");
        return sb.toString();
    }

}
