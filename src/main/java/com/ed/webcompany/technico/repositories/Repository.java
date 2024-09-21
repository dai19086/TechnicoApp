package com.ed.webcompany.technico.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> findById(Long id);

    List<T> findAll();

    Long save(T t);

    boolean deleteById(Long id);
}
