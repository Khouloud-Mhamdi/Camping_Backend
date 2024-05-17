package com.example.spring.camping.services;

import com.example.spring.camping.models.ManageUsers.DetailsUser;

import java.util.ArrayList;
import java.util.List;

public interface ICrud<T> {
    List<T> getAll();

    T getById (long id);
    T  add(T t);



    void delete(long id);
    T update(T t);


}
