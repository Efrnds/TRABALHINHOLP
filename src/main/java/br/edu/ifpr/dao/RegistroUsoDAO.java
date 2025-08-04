package br.edu.ifpr.dao;

import br.edu.ifpr.enums.Status;
import br.edu.ifpr.model.entity.RegistroUso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RegistroUsoDAO {
    private EntityManager em;

    public RegistroUsoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(RegistroUso registroUso) {
        em.getTransaction().begin();
        em.persist(registroUso);
        em.getTransaction().commit();
    }

    public void atualizar(RegistroUso  registroUso) {
        em.getTransaction().begin();
        em.merge(registroUso);
        em.getTransaction().commit();
    }

    public void deletar(RegistroUso registroUso) {
        em.getTransaction().begin();
        if (!em.contains(registroUso)) {
            registroUso = em.merge(registroUso);
        }
        em.remove(registroUso);
        em.getTransaction().commit();
    }

    public RegistroUso buscarPorId(int id) {
        return em.find(RegistroUso.class, id);
    }


    public List<RegistroUso> listarTodos() {
        TypedQuery<RegistroUso> query = em.createQuery("SELECT e FROM RegistroUso e", RegistroUso.class);
        return query.getResultList();
    }

    public int somarMinutosPorAlunoHoje(int alunoId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COALESCE(SUM(r.duracaoMin), 0) FROM RegistroUso r " +
                        "WHERE r.aluno.id = :id AND DATE(r.dataHora) = CURDATE()",
                Long.class
        );
        query.setParameter("id", alunoId);
        Long resultado = query.getSingleResult();
        return resultado != null ? resultado.intValue() : 0;
    }


    public int contarRegistrosAtivosAgora() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(r) FROM RegistroUso r " +
                        "WHERE r.dataHora <= CURRENT_TIMESTAMP " +
                        "AND FUNCTION('TIMESTAMPADD', 'MINUTE', r.duracaoMin, r.dataHora) > CURRENT_TIMESTAMP",
                Long.class
        );
        Long resultado = query.getSingleResult();
        return resultado != null ? resultado.intValue() : 0;
    }

}




