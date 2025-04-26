package com.myfood_api.domain.model.repository;

import com.myfood_api.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
