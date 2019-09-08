package br.com.projeto.sora.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.sora.model.entities.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

}
