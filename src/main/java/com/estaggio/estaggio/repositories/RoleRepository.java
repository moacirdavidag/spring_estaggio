package com.estaggio.estaggio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estaggio.estaggio.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
