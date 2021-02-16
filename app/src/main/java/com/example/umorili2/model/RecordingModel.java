package com.example.umorili2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class RecordingModel {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String site;

    private String desc;

    private String elementPureHtml;

    // на первоначальном этапе время создания пишем в site
    // при этом пишем только день без времени
    // private String TimeEntry;

    //***************

    public long getId() {return id; }

    public void setId(long id) { this.id = id; }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        sb.append("\n id:" + this.id);
        sb.append("\n site:" + this.site);
        sb.append("\n desc:" + this.desc);
        sb.append("\n elementPureHtml:" + this.elementPureHtml);
        sb.append("\n *****************");
        return sb.toString();
    }

}
