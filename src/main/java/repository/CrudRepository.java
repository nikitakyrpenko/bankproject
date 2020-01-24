package repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E> {

    Optional<E> findById(Integer id);

    Optional<List<E>> findAll();

    boolean save(E e);

    boolean update(E e);

    boolean deleteById(Integer id);

}
