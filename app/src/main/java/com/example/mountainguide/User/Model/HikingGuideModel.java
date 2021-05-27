package com.example.mountainguide.User.Model;

public class HikingGuideModel {
    String title, subtitle, desc;

    public HikingGuideModel() {
    }

    public HikingGuideModel(String title, String subtitle, String desc) {
        this.title = title;
        this.subtitle = subtitle;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}