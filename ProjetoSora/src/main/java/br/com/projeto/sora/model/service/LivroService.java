package br.com.projeto.sora.model.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.projeto.sora.model.entities.Livro;
import br.com.projeto.sora.model.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	public LivroRepository livroRepository;
	
	
	public Livro atualizarLivro(Long id,Livro livro) {
		Livro  livroSalvo = obterPeloCodigo(id);
		BeanUtils.copyProperties(livro, livroSalvo,"id");
		return livroRepository.save(livroSalvo);
	}
	
	
	public Livro obterPeloCodigo(Long id) {
		Livro livro = livroRepository.getOne(id);
		if(livro == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return livro;
	}

}
