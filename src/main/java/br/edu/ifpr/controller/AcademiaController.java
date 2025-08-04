package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AcademiaDAO;
import br.edu.ifpr.model.entity.Academia;

import java.util.List;

public class AcademiaController {

    private AcademiaDAO academiaDAO;

    public AcademiaController(AcademiaDAO academiaDAO){
        this.academiaDAO = academiaDAO;
    }

    public String cadastrarAcademia(String nome, int capacidade) {
        if(nome ==null || nome.trim().isEmpty()){
            return "Nome da academia não pode ser vazio";
        }

        if(capacidade <=0){
            return "Capacidade deve ser maior que zero.";
        }

        Academia academia = new Academia();
        academia.setNome(nome);
        academia.setCapacidade(capacidade);

        academiaDAO.salvar(academia);
        return "Academia cadastrada com sucesso";
    }

    public String atualizarAcademia(int id, String novoNome, int novaCapacidade) {
        Academia academia = academiaDAO.buscarPorId(id);
        if (academia == null){
            return "Academia não encontrada";
        }

        academia.setNome(novoNome);
        academia.setCapacidade(novaCapacidade);

        academiaDAO.atualizar(academia);
        return "Academia atualizada com sucesso";
    }

    public String excluirAcademia(int id) {
        Academia academia = academiaDAO.buscarPorId(id);
        if (academia == null){
            return "Academia não encontrada.";
        }

        academiaDAO.deletar(academia);
        return "Academia deletada com sucesso";
    }

    public Academia buscarPorId(int id){
        return academiaDAO.buscarPorId(id);
    }

    public List<Academia> listarAcademia(){
        return academiaDAO.listarTodos();
    }
}
