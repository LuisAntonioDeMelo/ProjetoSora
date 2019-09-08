package br.com.projeto.sora.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.sora.event.RecursoCriadoEvent;
import br.com.projeto.sora.model.entities.Editora;
import br.com.projeto.sora.model.repository.EditoraRepository;
import br.com.projeto.sora.model.service.EditoraService;

@CrossOrigin
@RestController
@RequestMapping("/editoras")
public class EditoraResource {

	@Autowired
	private EditoraRepository editoraRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private EditoraService editoraService;
	
	@GetMapping()
	public List<Editora> getAll(){
		return editoraRepository.findAll(); 
	}
	
	@PostMapping
	public ResponseEntity<Editora> criar(@Valid @RequestBody Editora editora, HttpServletResponse response){
		Editora editoraSalva = editoraRepository.save(editora);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, editoraSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(editoraSalva);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Editora> obterPorId(@PathVariable Long id){
		Editora editoraEncontrada = editoraRepository.findById(id).get();
		return editoraEncontrada != null? ResponseEntity.ok().body(editoraEncontrada) : ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id){
		editoraService.deletarPorId(id);
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Editora> atualizarEditora(@PathVariable Long id,@Valid @RequestBody Editora editora){
		Editora editoraAtualizada = editoraService.atualizarEditora(editora, id);
		return ResponseEntity.ok(editoraAtualizada);
	}
}
