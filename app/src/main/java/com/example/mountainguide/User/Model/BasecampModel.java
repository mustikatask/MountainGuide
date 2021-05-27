package com.example.mountainguide.User.Model;

public class BasecampModel {
    String id, id_gunung, name, addr, estimation_time, ticket, desc;

    public BasecampModel() {
    }

    public BasecampModel(String id, String id_gunung, String name, String addr, String estimation_time, String ticket, String desc) {
        this.id = id;
        this.id_gunung = id_gunung;
        this.name = name;
        this.addr = addr;
        this.estimation_time = estimation_time;
        this.ticket = ticket;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_gunung() {
        return id_gunung;
    }

    public void setId_gunung(String id_gunung) {
        this.id_gunung = id_gunung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEstimation_time() {
        return estimation_time;
    }

    public void setEstimation_time(String estimation_time) {
        this.estimation_time = estimation_time;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
