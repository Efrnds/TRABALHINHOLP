package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AcademiaDAO;
import br.edu.ifpr.model.entity.Academia;
import br.edu.ifpr.model.entity.Equipamento;
import br.edu.ifpr.dao.EquipamentoDAO;
import br.edu.ifpr.enums.Status;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@CrossOrigin(origins = "*")
public class EquipamentoController {

    private final EquipamentoDAO equipamentoDAO;
    private final AcademiaDAO academiaDAO;

    public EquipamentoController(EquipamentoDAO equipamentoDAO, AcademiaDAO academiaDAO) {
        this.equipamentoDAO = equipamentoDAO;
        this.academiaDAO = academiaDAO;
    }

    @PostMapping
    public String cadastrarEquipamento(@RequestParam("nome") String nome, @RequestParam("status") Status status, @RequestParam("academia_id") int academiaId) {
        Academia academia = academiaDAO.buscarPorId(academiaId);
        if (academia == null) {
            return "Academia não encontrada";
        }
        if (nome == null || nome.trim().isEmpty()) {
            return "Nome do equipamento não pode ser vazio.";
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setNome(nome);
        equipamento.setStatus(status);
        equipamento.setAcademia(academia);

        equipamentoDAO.salvar(equipamento);
        return "Equipamento cadastrado com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizarEquipamento(@PathVariable int id,
                                       @RequestParam String novoNome,
                                       @RequestParam Status novoStatus) {
        Equipamento equipamento = equipamentoDAO.buscarPorId(id);
        if (equipamento == null) {
            return "Equipamento não encontrado";
        }

        equipamento.setNome(novoNome);
        equipamento.setStatus(novoStatus);

        equipamentoDAO.atualizar(equipamento);
        return "Equipamento atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String excluirEquipamento(@PathVariable int id) {
        Equipamento equipamento = equipamentoDAO.buscarPorId(id);
        if (equipamento == null) {
            return "Equipamento não encontrado";
        }

        equipamentoDAO.deletar(equipamento);
        return "Equipamento deletado com sucesso!";
    }

    @GetMapping("/{id}")
    public Equipamento buscarEquipamentoPorId(@PathVariable int id) {
        return equipamentoDAO.buscarPorId(id);
    }

    @GetMapping
    public List<Equipamento> buscarEquipamentos() {
        return equipamentoDAO.listarTodos();
    }

    @PutMapping("/alternar-status/{id}")
    public String alternarStatus(@PathVariable int id) {
        Equipamento equipamento = equipamentoDAO.buscarPorId(id);
        if (equipamento == null) {
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
