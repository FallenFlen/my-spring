package com.flz.myspring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader == null ? Thread.currentThread().getContextClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Optional.ofNullable(this.classLoader.getResourceAsStream(path))
                .orElseThrow((() -> new FileNotFoundException("no classpath resource file found with path:" + path)));
    }

    public String getPath() {
        return path;
    }
}
