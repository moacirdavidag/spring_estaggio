package com.estaggio.estaggio.services;


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

import com.estaggio.estaggio.dtos.AlunoDTO;
import com.estaggio.estaggio.dtos.OrientadorDTO;
import com.estaggio.estaggio.entities.Aluno;
import com.estaggio.estaggio.entities.Orientador;
import com.estaggio.estaggio.repositories.OrientadorRepository;
import com.estaggio.estaggio.services.execptions.DatabaseException;
import com.estaggio.estaggio.services.execptions.ResourceNotFoundException;

@Service
public class OrientadorService {

    private static Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private OrientadorRepository orientadorRepository;

    @Transactional(readOnly = true)
    public Page<OrientadorDTO> findAllOrientadores(Pageable pageable) {
        Page<Orientador> list = orientadorRepository.findAll(pageable);
        return list.map(x -> new OrientadorDTO(x));
    }

    @Transactional(readOnly = true)
    public OrientadorDTO findOrientadorById(Long id) {
        Optional<Orientador> resultado = orientadorRepository.findById(id);
        Orientador orientador = resultado.orElseThrow(() -> new ResourceNotFoundException("Orientador não encontrado"));
        return new OrientadorDTO(orientador);
    }

    @Transactional
    public OrientadorDTO insert(OrientadorDTO OrientadorDTO) {
        Orientador orientador = new Orientador();
        dtoToEntity(OrientadorDTO, orientador);
        orientador = orientadorRepository.save(orientador);
        return new OrientadorDTO(orientador);
    }

    @Transactional
    public OrientadorDTO update(OrientadorDTO OrientadorDTO, Long id) {
        try {
            Orientador orientador = orientadorRepository.getOne(id);
            dtoToEntity(OrientadorDTO, orientador);
            orientador = orientadorRepository.save(orientador);
            return new OrientadorDTO(orientador);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
		try {
			orientadorRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

    public void dtoToEntity(OrientadorDTO dto, Orientador orientador) {
        orientador.setId(dto.getId());
        orientador.setMatricula(dto.getMatricula());
        orientador.setNome(dto.getNome());
        orientador.setOrientandos(dto.getOrientandos());
    }

}
