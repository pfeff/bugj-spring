package com.mbpfefferle.bugj.dao;

public interface Dao<T> {

    public void save(T t);
    public T find(int id);
}

