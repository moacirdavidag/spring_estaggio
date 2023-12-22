package com.estaggio.estaggio.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.estaggio.estaggio.entities.Aluno;
import com.estaggio.estaggio.entities.Orientador;

public class OrientadorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotBlank(message = "Campo obrigatório")
    private String matricula;

    private List<Aluno> orientandos = new ArrayList<Aluno>();

    public OrientadorDTO() {}

    public OrientadorDTO(Long id, @NotBlank(message = "Campo obrigatório") String nome,
            @NotBlank(message = "Campo obrigatório") String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    public OrientadorDTO(Long id, @NotBlank(message = "Campo obrigatório") String nome,
            @NotBlank(message = "Campo obrigatório") String matricula, List<Aluno> orientandos) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.orientandos = orientandos;
    }

    public OrientadorDTO(Orientador orientador) {
        this.id = orientador.getId();
        this.nome = orientador.getNome();
        this.matricula = orientador.getMatricula();
        this.orientandos = orientador.getOrientandos();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Aluno> getOrientandos() {
        return orientandos;
    }

    public void setOrientandos(List<Aluno> orientandos) {
        this.orientandos = orientandos;
    }
    
}
