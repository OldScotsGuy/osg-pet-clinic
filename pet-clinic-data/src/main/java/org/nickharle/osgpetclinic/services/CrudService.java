package org.nickharle.osgpetclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findall();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
