package curso.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.springboot.springboot.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %?1% ")
	public List<Pessoa> findPessoaByName(String nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %?1% and p.sexopessoa = ?2 ")
	public List<Pessoa> findPessoaByNameSexo(String nome , String sexopessoa);
}
