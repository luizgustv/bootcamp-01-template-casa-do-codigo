package br.com.treino.casadocodigo.model;

import br.com.treino.casadocodigo.request.CupomAplicado;
import br.com.treino.casadocodigo.validations.CpfOuCnpj;
import br.com.treino.casadocodigo.validations.ExistId;
import org.springframework.util.StringUtils;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotBlank String sobrenome;
    private @Email @NotBlank String email;
    @NotBlank
    @CpfOuCnpj(message = "Documento inválido")
    private String documento;
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}\\-\\d{4}$", message = "Telefone inválido")
    @NotBlank
    private String telefone;
    @NotNull
    @ExistId(className = Pais.class, fieldName = "id")
    private  Pais pais;
    @ExistId(className = Estado.class, fieldName = "id")
    private Estado estado;
    private @NotBlank String cidade;
    private @NotBlank String endereco;
    @Pattern(regexp = "\\d{5}\\-\\d{3}" ,message = "CEP inválido")
    @NotBlank
    private String cep;
    private @NotBlank String complemento;
    @NotNull
    private Pedido pedido; //1
    private CupomAplicado cupomAplicado; //2
    private BigDecimal totalComDesconto;

    @Deprecated
    public Compra(){}

    public Compra(@NotBlank String nome, @NotBlank String sobrenome,
                  @Email @NotBlank String email, @NotBlank String documento,
                  @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}\\-\\d{4}$",
                          message = "Telefone inválido") @NotBlank String telefone,
                  @NotNull Pais pais, @NotBlank String cidade,
                  @NotBlank String endereco, @Pattern(regexp = "\\d{5}\\-\\d{3}",
            message = "CEP inválido") @NotBlank String cep,
                  @NotBlank String complemento, @NotNull Pedido pedido) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.telefone = telefone;
        this.pais = pais;
        this.cidade = cidade;
        this.endereco = endereco;
        this.cep = cep;
        this.complemento = complemento;
        this.pedido = pedido;
    }

    public Long getId(){return id;}

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getComplemento() {
        return complemento;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public Pedido getPedido() {
        return pedido;
    }

    /*public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }*/

    public BigDecimal getTotalComDesconto() {
        BigDecimal total = pedido.getTotal();

        if (!StringUtils.isEmpty(cupomAplicado)){  //3
            return total.subtract(pedido.getTotal().
                    multiply(cupomAplicado.getPercentualDesconto())
                    .setScale(2, RoundingMode.CEILING));
        }
        return this.totalComDesconto = BigDecimal.ZERO;
    }

    public void aplicarCupom(Cupom cupom){
        this.cupomAplicado = new CupomAplicado(cupom);
    }



}
