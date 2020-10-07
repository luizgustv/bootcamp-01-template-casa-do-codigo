package br.com.treino.casadocodigo.model;

import br.com.treino.casadocodigo.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String codigo;
    @NotNull
    @Positive
    private BigDecimal percentualDesconto;
    @NotNull @Future
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    @Deprecated
    public Cupom(){}

    public Cupom(@NotBlank String codigo, @Positive BigDecimal percentualDesconto,
                 @Future LocalDate validade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }

    public String getCodigo() { return codigo; }

    public BigDecimal getPercentualDesconto() { return percentualDesconto; }

    public void setValidade(LocalDate validade) { this.validade = validade; }

    public LocalDate getValidade() { return validade; }

}
