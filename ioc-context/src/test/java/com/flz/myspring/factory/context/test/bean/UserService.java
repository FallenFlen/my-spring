package com.flz.myspring.factory.context.test.bean;

public class UserService {
    private UserDao userDao;
    private String company;
    private String location;

    public String queryUserInfo(String uid) {
        return userDao.queryUserInfo(uid);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
