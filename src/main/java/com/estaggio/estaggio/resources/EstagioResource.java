package com.estaggio.estaggio.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estaggio.estaggio.dtos.EstagioDTO;
import com.estaggio.estaggio.services.EstagioService;

@RestController
@RequestMapping(value = "/estagios")
public class EstagioResource {
    
    @Autowired
    private EstagioService estagioService;

    @GetMapping
    public ResponseEntity<Page<EstagioDTO>> findAll(Pageable pageable) {
        Page<EstagioDTO> list = estagioService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstagioDTO> findEstagioById(@PathVariable Long id) {
        EstagioDTO estagioDTO = estagioService.findEstagioById(id);
        return ResponseEntity.ok().body(estagioDTO);
    }

    @PostMapping
    public ResponseEntity<EstagioDTO> insert(@RequestBody @Valid EstagioDTO estagioDTO) {
        EstagioDTO dto = estagioService.insert(estagioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstagioDTO> update(@PathVariable Long id, @Valid @RequestBody EstagioDTO estagioDTO) {
        EstagioDTO dto = estagioService.update(estagioDTO, id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estagioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
