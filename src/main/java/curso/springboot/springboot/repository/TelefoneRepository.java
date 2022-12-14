package curso.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.springboot.springboot.model.Telefone;

@Repository
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {

	@Query("SELECT t FROM Telefone t where t.pessoa.id = ?1")
	public List<Telefone> getTelefones(Long pessoaid);
}
