package br.com.treino.casadocodigo.repository;

import br.com.treino.casadocodigo.model.Cupom;
import org.springframework.data.repository.CrudRepository;

public interface CupomRepositoy  extends CrudRepository<Cupom, Long> { //1

    public Cupom getByCodigo(String codigo);

}
