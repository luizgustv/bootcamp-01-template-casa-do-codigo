package br.com.treino.casadocodigo.model;

import br.com.treino.casadocodigo.validations.ExistId;
import br.com.treino.casadocodigo.validations.UniqueValue;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @UniqueValue(className = Estado.class, fieldName = "nome",
    message = "Já existe um estado com esse ID")
    private @NotBlank String nome;
    private @ManyToOne
    @NotNull
    @ExistId(className = Pais.class, fieldName = "id",
    message = "Não existe um estado com esse ID") long idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotNull long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public long getIdPais() {
        return idPais;
    }

    public Estado toModel(EntityManager entityManager){
        @NotNull Pais pais = entityManager.find(Pais.class, idPais);
        return new Estado(this.nome, pais);
    }

}
