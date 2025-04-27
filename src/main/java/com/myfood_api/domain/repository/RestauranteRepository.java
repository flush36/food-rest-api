package com.myfood_api.domain.repository;

import com.myfood_api.domain.model.Restaurante;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NonNullApi
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {


    @Override
    @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();

    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> buscarPorNome(String nome, @Param("id") Long cozinhaId);
}
