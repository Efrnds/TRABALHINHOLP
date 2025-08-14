package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AlunoDAO;
import br.edu.ifpr.model.entity.Aluno;
import br.edu.ifpr.enums.Plano;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    private AlunoDAO alunoDAO;

    public AlunoController(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    @PostMapping
    @Transactional
    public String cadastrarAluno(@RequestParam("nome") String nome,@RequestParam("plano") Plano plano){
        if(nome == null || nome.isEmpty()){
            return "Nome do aluno não pode ser vazio";
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setPlano(plano);

        alunoDAO.salvar(aluno);
        return "Aluno cadastrado com sucesso";
    }

    @PutMapping("/{id}")
    @Transactional
    public String atualizarAluno(@PathVariable int id,@RequestParam String novoNome,@RequestParam Plano novoPlano){
        Aluno aluno = alunoDAO.buscarPorId(id);
        if(aluno == null){
            return "Aluno não encontrado";
        }

        aluno.setNome(novoNome);
        aluno.setPlano(novoPlano);

        alunoDAO.atualizar(aluno);
        return "Aluno atualizado com sucesso";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String excluirAluno(@PathVariable int id){
        Aluno aluno = alunoDAO.buscarPorId(id);
        if(aluno == null){
            return "Aluno não encontrado";
        }

        alunoDAO.deletar(aluno);
        return "Aluno excluido com sucesso";
    }

    @GetMapping("/{id}")
    public Aluno buscarAlunoPorId(@PathVariable int id){
        return alunoDAO.buscarPorId(id);
    }

    @GetMapping
    public List<Aluno> listarAlunos(){
        return alunoDAO.listarTodos();
    }
}
