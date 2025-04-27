package com.myfood_api.domain.service;

import com.myfood_api.domain.exception.EntidadeEmUsoException;
import com.myfood_api.domain.model.Restaurante;
import com.myfood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarPorId(Long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long restauranteId) {
        try {
            Optional<Restaurante> restauranteOptional = buscarPorId(restauranteId);
            restauranteOptional.ifPresent(restaurante -> restauranteRepository.delete(restaurante));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de código %d não pode ser removido, pois está em uso", restauranteId));
        }
    }

    public List<Restaurante> buscarPorNome(String nome, Long cozinhaId) {
        return restauranteRepository.buscarPorNome(nome, cozinhaId);
    }
}
