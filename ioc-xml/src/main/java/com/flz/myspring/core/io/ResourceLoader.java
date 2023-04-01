package com.flz.myspring.core.io;

public interface ResourceLoader {
    String CLASS_PATH_PREFIX = "classpath:";

    Resource loadResource(String path);
}
