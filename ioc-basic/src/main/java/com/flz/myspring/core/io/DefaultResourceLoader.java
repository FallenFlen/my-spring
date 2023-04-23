package com.flz.myspring.core.io;

import java.util.Objects;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource loadResource(String path) {
        Objects.requireNonNull(path);
        if (path.startsWith(CLASS_PATH_PREFIX)) {
            return new ClassPathResource(path.substring(CLASS_PATH_PREFIX.length()));
        }
        return new FileResource(path);
    }
}
