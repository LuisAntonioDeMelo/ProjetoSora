package br.com.projeto.sora.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenhaAuth {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("luis7410"));
	}
}
