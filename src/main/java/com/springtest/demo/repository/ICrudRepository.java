package com.springtest.demo.repository;

import java.util.List;

public interface ICrudRepository<T> {
    void create(T t);
    List<T> readAll();
    T read(long id);
    void update(T t, int id);
    void delete(long id);
    long getLastId();

}
