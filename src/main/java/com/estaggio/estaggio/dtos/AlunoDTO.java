package com.estaggio.estaggio.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.estaggio.estaggio.entities.Aluno;
import com.estaggio.estaggio.entities.Estagio;
import com.estaggio.estaggio.entities.Orientador;


public class AlunoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotBlank(message = "Campo obrigatório")
    private String matricula;

    private Orientador orientador;

    public AlunoDTO() { }
    
    public AlunoDTO(Long id, @NotBlank(message = "Campo obrigatório") String nome,
            @NotBlank(message = "Campo obrigatório") String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    public AlunoDTO(Long id, @NotBlank(message = "Campo obrigatório") String nome,
            @NotBlank(message = "Campo obrigatório") String matricula, Orientador orientador) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.orientador = orientador;
    }

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.matricula = aluno.getMatricula();
        this.orientador = aluno.getOrientador();
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

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

}
