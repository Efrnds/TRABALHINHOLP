package br.edu.ifpr.dao;

import br.edu.ifpr.enums.Status;
import br.edu.ifpr.model.entity.Equipamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EquipamentoDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Equipamento equipamento) {
        em.persist(equipamento);
    }

    @Transactional
    public void atualizar(Equipamento equipamento) {
        em.merge(equipamento);
    }

    @Transactional
    public void deletar(Equipamento equipamento) {
        if (!em.contains(equipamento)) {
            equipamento = em.merge(equipamento);
        }
        em.remove(equipamento);
    }

    public Equipamento buscarPorId(int id) {
        return em.find(Equipamento.class, id);
    }

    public Status buscarStatusPorId(int equipamentoId) {
        Equipamento e = em.find(Equipamento.class, equipamentoId);
        return e != null ? e.getStatus() : null;
    }

    public List<Equipamento> listarTodos() {
        TypedQuery<Equipamento> query = em.createQuery("SELECT e FROM Equipamento e", Equipamento.class);
        return query.getResultList();
    }
}
