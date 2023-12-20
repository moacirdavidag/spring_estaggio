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
import com.estaggio.estaggio.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {
    
    @Autowired
    AlunoService alunoService;

    @GetMapping
	public ResponseEntity<Page<AlunoDTO>> findAll(Pageable pageable) {
		Page<AlunoDTO> list = alunoService.findAllAlunos(pageable);		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) {
		AlunoDTO dto = alunoService.findAlunoById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<AlunoDTO> insert(@RequestBody @Valid AlunoDTO dto) {
		AlunoDTO newDto = alunoService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody @Valid AlunoDTO dto) {
		AlunoDTO newDto = alunoService.update(dto, id);
		return ResponseEntity.ok().body(newDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
