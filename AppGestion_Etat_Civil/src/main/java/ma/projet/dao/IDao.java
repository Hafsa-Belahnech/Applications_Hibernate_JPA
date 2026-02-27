package ma.projet.dao;

import java.util.List;


//Interface générique pour les opérations CRUD de base

public interface IDao<T> {

   // Créer + Insérer un objet en base

    void create(T o);


    void update(T o);


    void delete(T o);


    T findById(int id);


    List<T> findAll();
}
