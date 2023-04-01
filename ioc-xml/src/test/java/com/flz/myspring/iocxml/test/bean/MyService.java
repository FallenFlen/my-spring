package com.flz.myspring.iocxml.test.bean;

public class MyService {
    private String name;
    private MyDao myDao;

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

    public MyDao getMyDao() {
        return myDao;
    }

    public void setMyDao(MyDao myDao) {
        this.myDao = myDao;
    }

    public String getUsernameById(String id) {
        return myDao.getUsernameById(id);
    }
}
