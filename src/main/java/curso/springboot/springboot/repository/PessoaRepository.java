package curso.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.springboot.springboot.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %?1% ")
	public List<Pessoa> findPessoaByName(String nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.sexopessoa = ?1 ")
	public List<Pessoa> findPessoaBySexo(String sexo);
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %?1% and p.sexopessoa = ?2 ")
	public List<Pessoa> findPessoaByNameSexo(String nome , String sexopessoa);
	
	
	default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		
		/*Estamos configurando a consulta para buscar por parte do nome no banco de dados igual o LIKE do SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*Une o objeto com o valor e a configuração para consultar*/
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
	}
	
	default Page<Pessoa> findPessoaByNameSexoPage(String nome,String sexo, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexopessoa(sexo);
		
		/*Estamos configurando a consulta para buscar por parte do nome no banco de dados igual o LIKE do SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("sexopessoa", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		/*Une o objeto com o valor e a configuração para consultar*/
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
	}
}
