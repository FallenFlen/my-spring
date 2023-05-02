package com.flz.myspring.factory.context.test.bean;

import java.util.Map;

public class UserDao {
    private static final Map<String, String> USER_INFO = Map.of(
            "1001", "context-1号",
            "1002", "context-2号",
            "1003", "context-3号",
            "1004", "context-4号"
    );

    public String queryUserInfo(String uid) {
        return USER_INFO.get(uid);
    }
}
