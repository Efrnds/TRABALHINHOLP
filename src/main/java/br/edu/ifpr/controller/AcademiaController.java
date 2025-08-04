package br.edu.ifpr.controller;

import br.edu.ifpr.dao.AcademiaDAO;
import br.edu.ifpr.model.entity.Academia;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/academia")
@CrossOrigin(origins = "*")
public class AcademiaController {

    private AcademiaDAO academiaDAO;

    public AcademiaController(AcademiaDAO academiaDAO){
        this.academiaDAO = academiaDAO;
    }

    @PostMapping
    public String cadastrarAcademia(@RequestParam("nome") String nome,@RequestParam("capacidade") int capacidade) {
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

    @PutMapping("/{id}")
    public String atualizarAcademia(@PathVariable int id,@RequestParam String novoNome,@RequestParam int novaCapacidade) {
        Academia academia = academiaDAO.buscarPorId(id);
        if (academia == null){
            return "Academia não encontrada";
        }

        academia.setNome(novoNome);
        academia.setCapacidade(novaCapacidade);

        academiaDAO.atualizar(academia);
        return "Academia atualizada com sucesso";
    }

    @DeleteMapping
    public String excluirAcademia(@PathVariable int id) {
        Academia academia = academiaDAO.buscarPorId(id);
        if (academia == null){
            return "Academia não encontrada.";
        }

        academiaDAO.deletar(academia);
        return "Academia deletada com sucesso";
    }

    @GetMapping("/{id}")
    public Academia buscarPorId(@PathVariable int id){
        return academiaDAO.buscarPorId(id);
    }

    @GetMapping
    public List<Academia> listarAcademia(){
        return academiaDAO.listarTodos();
    }
}
