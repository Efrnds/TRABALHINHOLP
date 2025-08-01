package br.edu.ifpr.dao;

import br.edu.ifpr.enums.Status;
import br.edu.ifpr.model.entity.Equipamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EquipamentoDAO {
    private EntityManager em;

    public EquipamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Equipamento equipamento) {
        em.getTransaction().begin();
        em.persist(equipamento);
        em.getTransaction().commit();
    }

    public void atualizar(Equipamento  equipamento) {
        em.getTransaction().begin();
        em.merge(equipamento);
        em.getTransaction().commit();
    }

    public void deletar(Equipamento equipamento) {
        em.getTransaction().begin();
        if (!em.contains(equipamento)) {
            equipamento = em.merge(equipamento);
        }
        em.remove(equipamento);
        em.getTransaction().commit();
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




