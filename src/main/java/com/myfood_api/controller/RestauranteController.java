package com.myfood_api.controller;

import com.myfood_api.domain.model.Cozinha;
import com.myfood_api.domain.model.Restaurante;
import com.myfood_api.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    @GetMapping
    public List<Restaurante> listar() {
        return cadastroRestauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long restauranteId) {
        return cadastroRestauranteService.buscarPorId(restauranteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
        return cadastroRestauranteService.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        return cadastroRestauranteService.buscarPorId(restauranteId).map(restauranteAtual -> {
            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formaPagamentos", "endereco", "dataCadastro");
            cadastroRestauranteService.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> delete(@PathVariable Long cozinhaId) {
        try {
            cadastroRestauranteService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/por-nome")
    public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId) {
        return cadastroRestauranteService.buscarPorNome(nome, cozinhaId);
    }

}
