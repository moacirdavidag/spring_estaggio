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

import com.estaggio.estaggio.dtos.EmpresaDTO;
import com.estaggio.estaggio.entities.Empresa;
import com.estaggio.estaggio.repositories.EmpresaRepository;
import com.estaggio.estaggio.services.execptions.DatabaseException;
import com.estaggio.estaggio.services.execptions.ResourceNotFoundException;

@Service
public class EmpresaService {
    
    private Logger logger = LoggerFactory.getLogger(EmpresaService.class); 

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    public Page<EmpresaDTO> findAll(Pageable pageable) {
        Page<Empresa> list = empresaRepository.findAll(pageable);
        return list.map(x -> new EmpresaDTO(x));
    }

    @Transactional(readOnly = true)
    public EmpresaDTO findEmpresaById(Long id) {
        Optional<Empresa> resultado = empresaRepository.findById(id);
        Empresa empresa = resultado.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));
        return new EmpresaDTO(empresa);
    }

    @Transactional
    public EmpresaDTO insert(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        dtoToEntity(empresaDTO, empresa);
        empresa = empresaRepository.save(empresa);
        return new EmpresaDTO(empresa);
    }

    @Transactional
    public EmpresaDTO update(EmpresaDTO empresaDTO, Long id) {
        try {
            Empresa empresa = empresaRepository.getOne(id);
            dtoToEntity(empresaDTO, empresa);
            empresa = empresaRepository.save(empresa);
            return new EmpresaDTO(empresa);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Empresa não encontrada");
        }
    }

    @Transactional
    public void delete(Long id) {
		try {
			empresaRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

    public void dtoToEntity(EmpresaDTO empresaDTO, Empresa empresa) {
        empresa.setId(empresaDTO.getId());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresa.setEstagios(empresaDTO.getEstagios());
    }

}
