package br.com.projeto.sora.model.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.projeto.sora.model.entities.Editora;
import br.com.projeto.sora.model.repository.EditoraRepository;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;
	
	public void deletarPorId(Long id) {
		Optional<Editora> edit = obterPeloCodigo(id);
		editoraRepository.delete(edit.get());
	}

	private Optional<Editora> obterPeloCodigo(Long id) {
		Optional<Editora> edit = editoraRepository.findById(id);
		if(edit.isEmpty()) {
			throw new DataIntegrityViolationException("Não e possivel deletar nenhum recuso encontrado!");
		}
		return edit;
	}
	
	public Editora atualizarEditora(Editora editora,Long id) {
		Optional<Editora> edit = obterPeloCodigo(id);
		BeanUtils.copyProperties(editora, edit.get(),"id");
		return editoraRepository.save(edit.get());
	}
	
}
