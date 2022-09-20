package curso.springboot.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.springboot.springboot.model.Profissao;

@Repository
public interface ProfissaoRepository extends CrudRepository<Profissao, Long> {

	
}
