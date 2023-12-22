package com.estaggio.estaggio.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.estaggio.estaggio.entities.Empresa;
import com.estaggio.estaggio.entities.Estagio;

public class EmpresaDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String razaoSocial;

    @NotBlank(message = "Campo obrigatório")
    private String cnpj;

    private List<Estagio> estagios;

    public EmpresaDTO() { }

    public EmpresaDTO(Long id, @NotBlank(message = "Campo obrigatório") String razaoSocial,
            @NotBlank(message = "Campo obrigatório") String cnpj) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public EmpresaDTO(Long id, @NotBlank(message = "Campo obrigatório") String razaoSocial,
            @NotBlank(message = "Campo obrigatório") String cnpj, List<Estagio> estagios) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.estagios = estagios;
    }

    public EmpresaDTO(Empresa empresa) {
        this.id = empresa.getId();
        this.cnpj = empresa.getCnpj();
        this.razaoSocial = empresa.getRazaoSocial();
        this.estagios = empresa.getEstagios();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
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
        EmpresaDTO other = (EmpresaDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EmpresaDTO [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", estagios=" + estagios
                + "]";
    }

}
