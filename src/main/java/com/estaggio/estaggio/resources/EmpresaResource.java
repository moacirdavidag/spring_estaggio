package com.estaggio.estaggio.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estaggio.estaggio.dtos.EmpresaDTO;
import com.estaggio.estaggio.services.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaResource {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<Page<EmpresaDTO>> findAll(Pageable pageable) {
        Page<EmpresaDTO> list = empresaService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpresaDTO> findEmpresaById(Long id) {
        EmpresaDTO empresaDTO = empresaService.findEmpresaById(id);
        return ResponseEntity.ok().body(empresaDTO);
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> insert(@RequestBody @Valid EmpresaDTO empresaDTO) {
        EmpresaDTO dto = empresaService.insert(empresaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmpresaDTO> update(@PathVariable @Valid EmpresaDTO empresaDTO, Long id) {
        EmpresaDTO dto = empresaService.update(empresaDTO, id);
        return ResponseEntity.ok().body(dto);
    }

    @Transactional(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
