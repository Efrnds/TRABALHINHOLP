package br.edu.ifpr.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RegistroUso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Temporal(TemporalType.DATE)
    private LocalDateTime dataHora;

    @Basic
    private int duracao_min;

    @ManyToMany
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToMany
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getDuracao_min() {
        return duracao_min;
    }

    public void setDuracao_min(int duracao_min) {
        this.duracao_min = duracao_min;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
