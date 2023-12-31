package com.estaggio.estaggio.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;

@Entity
@Table(name = "tb_orientador")
public class Orientador implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;

    @JsonIgnore
    @OneToMany(mappedBy = "orientador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Aluno> orientandos = new ArrayList<Aluno>();

    public Orientador() {}

    public Orientador(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Orientador other = (Orientador) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Orientador [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", orientandos=" + orientandos
                + "]";
    }

}
