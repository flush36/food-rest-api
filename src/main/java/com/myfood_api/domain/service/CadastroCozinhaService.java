package com.myfood_api.domain.service;

import com.myfood_api.domain.exception.EntidadeEmUsoException;
import com.myfood_api.domain.model.Cozinha;
import com.myfood_api.domain.model.CozinhasXmlWrapper;
import com.myfood_api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    public Optional<Cozinha> buscarPorId(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId);
    }


    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            Optional<Cozinha> cozinhaOptional = buscarPorId(cozinhaId);
            cozinhaOptional.ifPresent(cozinha -> cozinhaRepository.delete(cozinha));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }
}
