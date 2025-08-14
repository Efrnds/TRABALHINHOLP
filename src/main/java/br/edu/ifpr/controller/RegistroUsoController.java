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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/registro-uso")
@CrossOrigin(origins = "*")
public class RegistroUsoController {

    private final RegistroUsoDAO registroUsoDAO;
    private final AlunoDAO alunoDAO;
    private final EquipamentoDAO equipamentoDAO;
    private final AcademiaDAO academiaDAO;

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

    @PostMapping
    @Transactional
    public String registrarUso(
            @RequestParam("alunoId") int alunoId,
            @RequestParam("equipamentoId") int equipamentoId,
            @RequestParam("duracaoMin") int duracaoMin) {

        Aluno aluno = alunoDAO.buscarPorId(alunoId);
        Equipamento equipamento = equipamentoDAO.buscarPorId(equipamentoId);

        if (aluno == null || equipamento == null) {
            return "Aluno ou equipamento não encontrado.";
        }

        if (equipamento.getStatus() != Status.DISPONIVEL) {
            return "Equipamento em manutenção. Escolha outro.";
        }

        int minutosHoje = registroUsoDAO.somarMinutosPorAlunoHoje(alunoId);
        if (aluno.getPlano() == Plano.BASICO && (minutosHoje + duracaoMin) > 60) {
            return "Aluno com plano BÁSICO só pode treinar 60min por dia!";
        }

        if (equipamento.getAcademia() == null) {
            return "Erro: equipamento sem academia vinculada.";
        }

        int capacidadeMaxima = equipamento.getAcademia().getCapacidade();
        int ativosAgora = registroUsoDAO.contarRegistrosAtivosAgora();
        if (ativosAgora >= capacidadeMaxima) {
            return "Academia cheia. Aguarde vaga.";
        }

        RegistroUso registroUso = new RegistroUso();
        registroUso.setAluno(aluno);
        registroUso.setEquipamento(equipamento);
        registroUso.setDuracao_min(duracaoMin);
        registroUso.setDataHora(LocalDateTime.now());

        registroUsoDAO.salvar(registroUso);
        return "Uso registrado com sucesso.";
    }

    @GetMapping
    public List<RegistroUso> listarRegistros() {
        return registroUsoDAO.listarTodos();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirRegistro(@PathVariable(name = "id") int id) {
        RegistroUso r = registroUsoDAO.buscarPorId(id);
        if (r != null) {
            registroUsoDAO.deletar(r);
        }
    }
}
