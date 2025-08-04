package br.edu.ifpr.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "RegistroUso.somarMinutosPorAlunoHoje",
                query = "SELECT COALESCE(SUM(r.duracao_min), 0) FROM RegistroUso r " +
                        "WHERE r.aluno.id = :alunoId AND FUNCTION('DATE', r.dataHora) = FUNCTION('CURRENT_DATE')"
        )
})
public class RegistroUso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime dataHora;

    @Basic
    private int duracao_min;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
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
