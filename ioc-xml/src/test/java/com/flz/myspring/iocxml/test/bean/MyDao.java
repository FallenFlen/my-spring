package com.flz.myspring.iocxml.test.bean;

import java.util.Map;

public class MyDao {
    private static final Map<String, String> data;

    static {
        data = Map.of(
                "10000", "user1",
                "10001", "user2",
                "10002", "user3"
        );
    }

    public String getUsernameById(String id) {
        return data.get(id);
    }
}
