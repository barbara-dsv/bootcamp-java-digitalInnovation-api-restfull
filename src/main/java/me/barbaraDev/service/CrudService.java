package me.barbaraDev.service;


import java.util.List;

public interface CrudService<ID, T> {
    T findById(ID id);
    T create(T entity);
    List<T> findAll();
    T update (ID id, T entity);
    void delete(ID id);
}
