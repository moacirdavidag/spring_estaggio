package com.estaggio.estaggio.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.estaggio.estaggio.entities.Aluno;
import com.estaggio.estaggio.entities.Empresa;
import com.estaggio.estaggio.entities.Estagio;
import com.estaggio.estaggio.entities.Orientador;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EstagioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;
    @NotBlank(message = "Campo obrigatório")
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo obrigatório")
    private Date dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo obrigatório")
    private Date dataFim;

    private Orientador orientador;
    private Aluno aluno;
    private Empresa empresa;

    public EstagioDTO() {
    }

    public EstagioDTO(Long id, @NotBlank(message = "Campo obrigatório") String descricao,
            @NotBlank(message = "Campo obrigatório") String status,
            @NotBlank(message = "Campo obrigatório") Date dataInicio,
            @NotBlank(message = "Campo obrigatório") Date dataFim, Orientador orientador) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.orientador = orientador;
    }

    public EstagioDTO(Long id, @NotBlank(message = "Campo obrigatório") String descricao,
            @NotBlank(message = "Campo obrigatório") String status,
            @NotBlank(message = "Campo obrigatório") Date dataInicio,
            @NotBlank(message = "Campo obrigatório") Date dataFim, Orientador orientador, Aluno aluno,
            Empresa empresa) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.orientador = orientador;
        this.aluno = aluno;
        this.empresa = empresa;
    }

    public EstagioDTO(Estagio estagio) {
        this.id = estagio.getId();
        this.descricao = estagio.getDescricao();
        this.status = estagio.getStatus();
        this.dataInicio = estagio.getDataInicio();
        this.dataFim = estagio.getDataFim();
        this.orientador = estagio.getOrientador();
        this.aluno = estagio.getAluno();
        this.empresa = estagio.getEmpresa();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        EstagioDTO other = (EstagioDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EstagioDTO [id=" + id + ", descricao=" + descricao + ", status=" + status + ", dataInicio=" + dataInicio
                + ", dataFim=" + dataFim + ", orientador=" + orientador + ", aluno=" + aluno + ", empresa=" + empresa
                + "]";
    }

}
