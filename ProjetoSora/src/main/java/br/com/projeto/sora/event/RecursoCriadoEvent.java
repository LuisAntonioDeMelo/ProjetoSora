package br.com.projeto.sora.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = -1458124381408698763L;

	private HttpServletResponse response;
	private Long codigo;
	
	public RecursoCriadoEvent(Object source,HttpServletResponse response,Long codigo) {
		super(source);
		this.codigo = codigo;
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	

}
