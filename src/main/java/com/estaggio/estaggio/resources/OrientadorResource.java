package com.estaggio.estaggio.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estaggio.estaggio.dtos.AlunoDTO;
import com.estaggio.estaggio.dtos.OrientadorDTO;
import com.estaggio.estaggio.services.OrientadorService;

@RestController
@RequestMapping(value = "/orientadores")
public class OrientadorResource {

    @Autowired
    private OrientadorService orientadorService;

    @GetMapping
    public ResponseEntity<Page<OrientadorDTO>> findAll(Pageable pageable) {
        Page<OrientadorDTO> list = orientadorService.findAllOrientadores(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrientadorDTO> findById(@PathVariable Long id) {
        OrientadorDTO orientadorDTO = orientadorService.findOrientadorById(id);
        return ResponseEntity.ok().body(orientadorDTO);
    }

    @PostMapping
    public ResponseEntity<OrientadorDTO> insert(@RequestBody @Valid OrientadorDTO orientadorDTO) {
        OrientadorDTO dto = orientadorService.insert(orientadorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
	public ResponseEntity<OrientadorDTO> update(@PathVariable Long id, @RequestBody @Valid OrientadorDTO dto) {
		OrientadorDTO newDto = orientadorService.update(dto, id);
		return ResponseEntity.ok().body(newDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		orientadorService.delete(id);
		return ResponseEntity.noContent().build();
	}
    
}
