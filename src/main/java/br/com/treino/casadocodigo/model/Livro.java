package br.com.treino.casadocodigo.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private @NotBlank String titulo;
    private @NotBlank @Size(max = 500) String resumo;
    private String sumario; //obrigatorio?
    private @NotNull @Min(20) BigDecimal preco;
    private @NotNull @Min(100)  Integer numPaginas;
    private @NotBlank String isbn;

    private @Future @NotNull LocalDate dataPublicacao;
    private @NotNull @ManyToOne @Valid Categoria categoria;
    private @NotNull @ManyToOne @Valid Autor autor;

    @Deprecated
    public Livro(){}

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                 String sumario, @NotNull @Min(20) BigDecimal preco,
                 @NotNull @Min(100) Integer numPaginas, @NotBlank String isbn,
                 @Future @NotNull LocalDate dataPublicacao, Categoria categoria, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numPaginas=" + numPaginas +
                ", isbn=" + isbn +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() { return dataPublicacao; }

}