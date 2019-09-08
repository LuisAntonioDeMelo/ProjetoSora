package br.com.projeto.sora.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.sora.event.RecursoCriadoEvent;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();
		this.adicionarHeader(response, codigo);
	
	}

	private void adicionarHeader(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").
				buildAndExpand(codigo).toUri();
		response.setHeader("Location",uri.toASCIIString());
	}

}


