package com.estaggio.estaggio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estaggio.estaggio.entities.Estagio;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, Long> {
    
}
