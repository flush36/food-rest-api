package com.myfood_api.controller;

import com.myfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.myfood_api.domain.model.Cozinha;
import com.myfood_api.domain.repository.CozinhaRepository;
import com.myfood_api.domain.model.CozinhasXmlWrapper;
import com.myfood_api.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cadastroCozinhaService.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return cadastroCozinhaService.listarXml();
    }

    @GetMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        return cadastroCozinhaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.buscarPorId(cozinhaId).map(cozinhaAtual -> {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cozinhaRepository.save(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }).orElse(ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{cozinhaId}")
//    public ResponseEntity<Cozinha> delete(@PathVariable Long cozinhaId) {
//        try {
//            cadastroCozinhaService.excluir(cozinhaId);
//            return ResponseEntity.noContent().build();
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinhaService.excluir(cozinhaId);
        } catch (EntidadeNaoEncontradaException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
