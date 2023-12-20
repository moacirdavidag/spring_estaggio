package com.estaggio.estaggio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estaggio.estaggio.entities.Orientador;

@Repository
public interface OrientadorRepository extends JpaRepository<Orientador, Long> {
    
}
