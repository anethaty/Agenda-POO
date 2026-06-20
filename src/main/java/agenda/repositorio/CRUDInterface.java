package agenda.repositorio;

import java.util.List;

public interface CRUDInterface<T> {
    void create(T obj);
    T read(int id);
    T update(T obj);
    void delete(T obj);
    List<T> readAll();
}