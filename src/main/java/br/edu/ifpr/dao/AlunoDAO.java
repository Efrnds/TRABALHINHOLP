package br.edu.ifpr.dao;

import br.edu.ifpr.model.entity.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AlunoDAO {

    private EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void atualizar(Aluno aluno) {
        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
    }

    public void deletar(Aluno aluno) {
        em.getTransaction().begin();
        if (!em.contains(aluno)) {
            aluno = em.merge(aluno);
        }
        em.remove(aluno);
        em.getTransaction().commit();
    }

    public Aluno buscarPorId(int id) {
        return em.find(Aluno.class, id);
    }

    public List<Aluno> listarTodos() {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }
}
