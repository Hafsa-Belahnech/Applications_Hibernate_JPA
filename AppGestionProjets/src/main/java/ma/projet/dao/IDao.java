package ma.projet.dao;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;

import java.util.List;

public interface IDao<T> {
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    T findById(int id);

    EmployeTache findById(EmployeTachePK id);

    List<T> findAll();
}