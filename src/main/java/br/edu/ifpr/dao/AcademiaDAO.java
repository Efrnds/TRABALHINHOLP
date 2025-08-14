package br.edu.ifpr.dao;

import br.edu.ifpr.model.entity.Academia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AcademiaDAO {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Academia academia) {
        em.persist(academia);
    }

    public void atualizar(Academia academia) {
        em.merge(academia);
    }

    public void deletar(Academia academia) {
        if (!em.contains(academia)) {
            academia = em.merge(academia);
        }
        em.remove(academia);
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
