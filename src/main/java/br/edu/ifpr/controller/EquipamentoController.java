package br.edu.ifpr.controller;

import br.edu.ifpr.model.entity.Equipamento;
import br.edu.ifpr.dao.EquipamentoDAO;
import br.edu.ifpr.enums.Status;

import java.util.List;

public class EquipamentoController {

    private EquipamentoDAO equipamentoDAO;

    public EquipamentoController(EquipamentoDAO equipamentoDAO) {
        this.equipamentoDAO = equipamentoDAO;
    }

    public String cadastrarEquipamento(String nome, Status status){
        if(nome == null || nome.trim().isEmpty()){
            return "Nome do equipamento não pode ser vazio.";
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setNome(nome);
        equipamento.setStatus(status);

        equipamentoDAO.salvar(equipamento);
        return "Equipamento Cadastrado com sucesso!";
    }

    public String atualizarEquipamento(int id, String novoNome, Status novoStatus){
        Equipamento equipamento = equipamentoDAO.buscarPorId(id);
        if (equipamento == null){
            return "Equipamento não encontrado";
        }

        equipamento.setNome(novoNome);
        equipamento.setStatus(novoStatus);

        equipamentoDAO.atualizar(equipamento);
        return "Equipamento Atualizado com sucesso!";
    }

    public String excluirEquipamento(int id){
        Equipamento equipamento = equipamentoDAO.buscarPorId(id);
        if (equipamento == null){
            return "Equipamento não encontrado";
        }

        equipamentoDAO.deletar(equipamento);
        return "Equipamento Deletado com sucesso!";
    }

    public Equipamento buscarEquipamentoPorId(int id){
        return equipamentoDAO.buscarPorId(id);
    }

    public List<Equipamento> buscarEquipamentos(){
        return equipamentoDAO.listarTodos();
    }

    public String alternarStatus(int id) {
        Equipamento equipamento = equipamentoDAO.buscarPorId(id);
        if (equipamento == null){
            return "Equipamento não encontrado";
        }

        if (equipamento.getStatus() == Status.DISPONIVEL) {
            equipamento.setStatus(Status.MANUTENCAO);
        } else {
            equipamento.setStatus(Status.DISPONIVEL);
        }

        equipamentoDAO.atualizar(equipamento);
        return "Status do equipamento atualizado para " + equipamento.getStatus();
    }
}
