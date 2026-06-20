package agenda.repositorio;

import agenda.util.Util;
import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class Repositorio<T> implements CRUDInterface<T> {
    
    protected EntityManager manager;
    protected Class<T> tipo;

    public Repositorio(Class<T> tipo) {
        this.manager = Util.conectarBanco();
        this.tipo = tipo;
    }

    @Override
    public void create(T obj) {
        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }

    @Override
    public T read(int id) {
        return manager.find(tipo, id);
    }

    @Override
    public T update(T obj) {
        manager.getTransaction().begin();
        T objAtualizado = manager.merge(obj); 
        manager.getTransaction().commit();
        return objAtualizado;
    }

    @Override
    public void delete(T obj) {
        manager.getTransaction().begin();
        manager.remove(manager.contains(obj) ? obj : manager.merge(obj));
        manager.getTransaction().commit();
    }

    @Override
    public List<T> readAll() {
        String jpql = "SELECT obj FROM " + tipo.getSimpleName() + " obj";
        return manager.createQuery(jpql, tipo).getResultList();
    }
}