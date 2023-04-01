package com.flz.myspring.ioc.beans.exception;

public class BeansException extends RuntimeException {

    public BeansException(Throwable e) {
        super(e);
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable e) {
        super(message, e);
    }
}
