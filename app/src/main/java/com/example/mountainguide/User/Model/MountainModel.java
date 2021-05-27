package com.example.mountainguide.User.Model;

import android.graphics.Bitmap;

public class MountainModel {

    String id, name, status, height, geometry, location, terrain, weather, desc, note, imgurl;
    public Double latitude;
    public Double longitude;

    public MountainModel() {
    }

    public MountainModel(String id,String name, String status,String height, String desc,
                         String note, String location, String terrain, String geometry,
                         String weather, String imgurl, Double latitude, Double longitude) {
        this.name = name;
        this.status = status;
        this.height = height;
        this.location = location;
        this.terrain = terrain;
        this.geometry = geometry;
        this.weather = weather;
        this.desc = desc;
        this.note = note;
        this.longitude = longitude;
        this.latitude= latitude;
        this.imgurl = imgurl;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
