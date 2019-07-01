package ro.ubbcluj.inmemory;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T, ID> {

    T save(T object);

    Optional<T> findById(ID id);

    List<T> findAll();

    void remove(T object);
}
