package br.com.projeto.sora.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.sora.model.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

//	@Query("select l from livro inner join l.editora edi")
//	public List<Livro> obterTodos();
}
