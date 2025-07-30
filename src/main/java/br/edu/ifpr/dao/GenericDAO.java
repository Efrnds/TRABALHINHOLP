/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpr.dao;

import br.edu.ifpr.util.PersistenceUtil;
import jakarta.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class GenericDAO<PK, T>{
 
    protected EntityManager em;

    public GenericDAO() {
        em = PersistenceUtil.getEntityManager();
    }
    
    public void create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
    
    public T retrive(PK pk) {
        T entity = (T) em.find(getTypeClass(), pk);
        return entity;
    }
    
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }
    
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
    
    public List<T> findAll() {
        List<T> entities = em.createQuery("FROM " +
                getTypeClass().getName()).getResultList();
        return entities;
    }
    
    public Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1];
        return clazz;
    }
    
}
