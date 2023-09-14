package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

//    @GetMapping
//    public List<DadosListagemMedico> listar(){
//        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
//    }

//    @GetMapping// find all
//    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {//com pageable ele faz a paginação automaticamente
//        return repository.findAll(paginacao).map(DadosListagemMedico::new);
//    }

    @GetMapping// find all
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {//com pageable ele faz a paginação automaticamente
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);//ao criar a consulta, se atentar ao nome da coluna e ao valor dela
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);//dessa forma o sistema devido estar sob uma transacao, ele entende que existem dados a serem atualizados
    }

//    @DeleteMapping("/{id}")//espera um parametro dinamico da url, para a exclusão fisica
//    @Transactional
//    public void excluir(@PathVariable Long id){//anotado com o Path para saber que é um parametro da URL
//        repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")//espera um parametro dinamico da url, para a exclusão fisica
    @Transactional
    public void excluir(@PathVariable Long id){//anotado com o Path para saber que é um parametro da URL
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
