package br.edu.ifpr.dao;

import br.edu.ifpr.model.entity.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoDAO {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Aluno aluno) {
        em.persist(aluno);
    }

    public void atualizar(Aluno aluno) {
        em.merge(aluno);
    }

    public void deletar(Aluno aluno) {
        if (!em.contains(aluno)) {
            aluno = em.merge(aluno);
        }
        em.remove(aluno);
    }

    public Aluno buscarPorId(int id) {
        return em.find(Aluno.class, id);
    }

    public List<Aluno> listarTodos() {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }
}
