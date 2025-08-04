package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AlunoDAO;
import br.edu.ifpr.dao.AcademiaDAO;
import br.edu.ifpr.dao.EquipamentoDAO;
import br.edu.ifpr.dao.RegistroUsoDAO;
import br.edu.ifpr.model.entity.Aluno;
import br.edu.ifpr.model.entity.Equipamento;
import br.edu.ifpr.model.entity.RegistroUso;
import br.edu.ifpr.enums.Plano;
import br.edu.ifpr.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public class RegistroUsoController {

    private RegistroUsoDAO registroUsoDAO;
    private AlunoDAO alunoDAO;
    private EquipamentoDAO equipamentoDAO;
    private AcademiaDAO academiaDAO;

    public RegistroUsoController(
            RegistroUsoDAO registroUsoDAO,
            AlunoDAO alunoDAO,
            EquipamentoDAO equipamentoDAO,
            AcademiaDAO academiaDAO
    ) {
        this.registroUsoDAO = registroUsoDAO;
        this.alunoDAO = alunoDAO;
        this.equipamentoDAO = equipamentoDAO;
        this.academiaDAO = academiaDAO;
    }

    public String registrarUso(int alunoId, int equipamentoId, int duracaoMin){
        Aluno aluno = alunoDAO.buscarPorId(alunoId);
        Equipamento equipamento = equipamentoDAO.buscarPorId(equipamentoId);

//        verifica se o equipamento está disponível
        if  (equipamento.getStatus() != Status.DISPONIVEL) {
            return "Equipamento em manutenção. Escolha outro.";
        }

//        verificar o tempo usado hoje
        int minutosHoje = registroUsoDAO.somarMinutosPorAlunoHoje(alunoId);
        if (aluno.getPlano() == Plano.BASICO && (minutosHoje + duracaoMin) > 60) {
            return "Aluno com plano BÁSICO só pode treinar 60min por dia!";
        }

//        verificar capacidade atual
        int capacidadeMaxima = equipamento.getAcademia().getCapacidade();
        int ativosAgora = registroUsoDAO.contarRegistrosAtivosAgora();
        if (ativosAgora > capacidadeMaxima) {
            return "Academia cheia. Aguarde vaga.";
        }

//        criar novo registro
        RegistroUso registroUso = new RegistroUso();
        registroUso.setAluno(aluno);
        registroUso.setEquipamento(equipamento);
        registroUso.setDuracao_min(duracaoMin);
        registroUso.setDataHora(LocalDateTime.now());

        registroUsoDAO.salvar(registroUso);
        return "Uso registrado com sucesso.";
    }

    public List<RegistroUso> listarRegistros(){
        return registroUsoDAO.listarTodos();
    }

    public void excluirRegistro(int id){
        RegistroUso r = registroUsoDAO.buscarPorId(id);
        if (r != null) {
            registroUsoDAO.deletar(r);
        }
    }
}
