package com.estaggio.estaggio.services;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estaggio.estaggio.dtos.EmpresaDTO;
import com.estaggio.estaggio.dtos.EstagioDTO;
import com.estaggio.estaggio.entities.Estagio;
import com.estaggio.estaggio.repositories.EstagioRepository;
import com.estaggio.estaggio.services.execptions.DatabaseException;
import com.estaggio.estaggio.services.execptions.ResourceNotFoundException;

@Service
public class EstagioService implements Serializable {
    
    private Logger logger = LoggerFactory.getLogger(EstagioService.class);

    @Autowired
    private EstagioRepository estagioRepository;

    @Transactional(readOnly = true)
    public Page<EstagioDTO> findAll(Pageable pageable) {
        Page<Estagio> list = estagioRepository.findAll(pageable);
        return list.map(x -> new EstagioDTO(x));
    }

    @Transactional(readOnly = true)
    public EstagioDTO findEstagioById(Long id) {
        Optional<Estagio> resultado = estagioRepository.findById(id);
        Estagio estagio = resultado.orElseThrow(() -> new ResourceNotFoundException("Estágio não encontrado"));
        return new EstagioDTO(estagio); 
    }

    @Transactional
    public EstagioDTO insert(EstagioDTO estagioDTO) {
        Estagio estagio = new Estagio();
        dtoToEntity(estagioDTO, estagio);
        estagio = estagioRepository.save(estagio);
        return new EstagioDTO(estagio);
    }

    @Transactional
    public EstagioDTO update(EstagioDTO estagioDTO, Long id) {
        try {
            Estagio estagio = estagioRepository.getOne(id);
            dtoToEntity(estagioDTO, estagio);
            estagio = estagioRepository.save(estagio);
            return new EstagioDTO(estagio);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Estágio não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
		try {
			estagioRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

    public void dtoToEntity(EstagioDTO estagioDTO, Estagio estagio) {
        estagio.setDescricao(estagioDTO.getDescricao());
        estagio.setId(estagioDTO.getId());
        estagio.setDataInicio(estagioDTO.getDataInicio());
        estagio.setDataFim(estagio.getDataFim());
        estagio.setAluno(estagioDTO.getAluno());
        estagio.setOrientador(estagioDTO.getOrientador());
        estagio.setEmpresa(estagioDTO.getEmpresa());
        estagio.setStatus(estagioDTO.getStatus());
    }
}
