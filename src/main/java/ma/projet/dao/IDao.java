package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
    boolean create(T obj);
    T getById(int id);
    List<T> getAll();
}
