package br.edu.ifpr.dao;

import br.edu.ifpr.model.entity.Academia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AcademiaDAO {
    private EntityManager em;

    public AcademiaDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Academia academia) {
        em.getTransaction().begin();
        em.persist(academia);
        em.getTransaction().commit();
    }

    public void atualizar(Academia academia) {
        em.getTransaction().begin();
        em.merge(academia);
        em.getTransaction().commit();
    }

    public void deletar(Academia academia) {
        em.getTransaction().begin();
        if (!em.contains(academia)) {
            academia = em.merge(academia);
        }
        em.remove(academia);
        em.getTransaction().commit();
    }

    public Academia buscarPorId(int id) {
        return em.find(Academia.class, id);
    }

    public int buscarCapacidadeMaxima(int academiaId) {
        Academia a = em.find(Academia.class, academiaId);
        return a != null ? a.getCapacidade() : 0;
    }

    public List<Academia> listarTodos() {
        TypedQuery<Academia> query = em.createQuery("SELECT a FROM Academia a", Academia.class);
        return query.getResultList();
    }
}
