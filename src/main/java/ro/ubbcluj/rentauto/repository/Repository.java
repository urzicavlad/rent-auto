package ro.ubbcluj.rentauto.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    T save(T object);

    Optional<T> findById(ID id);

    List<T> findAll();

    void remove(T object);
}
