package ma.projet.dao;

import java.util.List;

//Méthodes qui seront implémentées après
public interface IDao<T> {
    //Create
    boolean create(T entity);
    //Update
    boolean update(T entity);
    //Delete
    boolean delete(T entity);
    //Recherche par id
    T findById(int id);
    List<T> findAll();
}
