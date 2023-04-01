package com.flz.myspring.beans.test.bean;

public class MyService {
    private String name;

    public MyService(String name) {
        this.name = name;
    }

    public MyService() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
