package br.edu.ifpr.dao;

import br.edu.ifpr.model.entity.RegistroUso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistroUsoDAO {

    @PersistenceContext
    private EntityManager em;

    public void salvar(RegistroUso registroUso) {
        em.persist(registroUso);
    }

    public void atualizar(RegistroUso registroUso) {
        em.merge(registroUso);
    }

    public void deletar(RegistroUso registroUso) {
        if (!em.contains(registroUso)) {
            registroUso = em.merge(registroUso);
        }
        em.remove(registroUso);
    }

    public RegistroUso buscarPorId(int id) {
        return em.find(RegistroUso.class, id);
    }

    public List<RegistroUso> listarTodos() {
        TypedQuery<RegistroUso> query = em.createQuery("SELECT r FROM RegistroUso r", RegistroUso.class);
        return query.getResultList();
    }

    public int somarMinutosPorAlunoHoje(int alunoId) {
        TypedQuery<Long> query = em.createNamedQuery("RegistroUso.somarMinutosPorAlunoHoje", Long.class);
        query.setParameter("alunoId", alunoId);
        Long resultado = query.getSingleResult();
        return resultado != null ? resultado.intValue() : 0;
    }

    public int contarRegistrosAtivosAgora() {
        String sql = "SELECT COUNT(*) FROM registro_uso r " +
                     "WHERE r.data_hora <= CURRENT_TIMESTAMP " +
                     "AND DATE_ADD(r.data_hora, INTERVAL r.duracao_min MINUTE) > CURRENT_TIMESTAMP";

        Query query = em.createNativeQuery(sql);
        Number result = (Number) query.getSingleResult();
        return result != null ? result.intValue() : 0;
    }
}
