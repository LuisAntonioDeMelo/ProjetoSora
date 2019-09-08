package br.com.projeto.sora.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.projeto.sora.model.entities.Livro;
import br.com.projeto.sora.model.repository.LivroRepository;
import br.com.projeto.sora.model.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LivroService livroService;

	//obter todos
	@GetMapping
	public List<Livro> obterTodos(){
		return livroRepository.findAll(); 
	}
	
	///salvar novo livro
	@PostMapping
	public ResponseEntity<Livro> criarLivro(@Valid @RequestBody Livro livro, HttpServletResponse resp){
		Livro livroSalvo = livroRepository.save(livro);
		publisher.publishEvent(new RecursoCriadoEvent(this,resp,livroSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> obterPorId(@PathVariable Long id){
		Livro livro = livroRepository.getOne(id);
		return livro != null? ResponseEntity.status(HttpStatus.OK).body(livro): ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarLivro(@PathVariable Long id) {
		livroRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Livro> atualizar(@PathVariable Long id,@Valid @RequestBody Livro livro){
		Livro livroAtualizado = livroService.atualizarLivro(id, livro);
		return ResponseEntity.ok(livroAtualizado);
	}
	
}
