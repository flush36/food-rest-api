package com.myfood_api.controller;

import com.myfood_api.domain.model.Cozinha;
import com.myfood_api.domain.model.repository.CozinhaRepository;
import com.myfood_api.model.CozinhasXmlWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    @GetMapping(value = "/{cozinhaId}")
    public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
        return cozinhaRepository.findById(id).orElse(null);
    }
}
