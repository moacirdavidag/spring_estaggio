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
import com.estaggio.estaggio.entities.Aluno;
import com.estaggio.estaggio.repositories.AlunoRepository;
import com.estaggio.estaggio.services.execptions.DatabaseException;
import com.estaggio.estaggio.services.execptions.ResourceNotFoundException;

@Service
public class AlunoService {

    private static Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public Page<AlunoDTO> findAllAlunos(Pageable pageable) {
        Page<Aluno> list = alunoRepository.findAll(pageable);
        return list.map(x -> new AlunoDTO(x));
    }

    @Transactional(readOnly = true)
    public AlunoDTO findAlunoById(Long id) {
        Optional<Aluno> resultado = alunoRepository.findById(id);
        Aluno aluno = resultado.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        return new AlunoDTO(aluno);
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        dtoToEntity(alunoDTO, aluno);
        aluno = alunoRepository.save(aluno);
        return new AlunoDTO(aluno);
    }

    @Transactional
    public AlunoDTO update(AlunoDTO alunoDTO, Long id) {
        try {
            Aluno aluno = alunoRepository.getOne(id);
            dtoToEntity(alunoDTO, aluno);
            aluno = alunoRepository.save(aluno);
            return new AlunoDTO(aluno);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
		try {
			alunoRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

    public void dtoToEntity(AlunoDTO alunoDTO, Aluno aluno) {
        aluno.setId(alunoDTO.getId());
        aluno.setNome(alunoDTO.getNome());
        aluno.setMatricula(alunoDTO.getMatricula());
        aluno.setOrientador(alunoDTO.getOrientador());
    }

}
