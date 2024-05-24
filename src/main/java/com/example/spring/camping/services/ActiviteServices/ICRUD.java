package com.example.spring.camping.services.ActiviteServices;

import java.util.List;
import java.util.Optional;

public interface ICRUD<Object> {
    Object Add(Object o);
    Object Update(Object o);
    List<Object> RetrieveAll();
    void Delete(long id);
    Optional<Object> RetrieveById(long id);
}



