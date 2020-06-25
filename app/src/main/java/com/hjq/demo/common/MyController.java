package com.hjq.demo.common;

public interface MyController<T> {

    public void initModel(T model);

    public void saveModel();
}
