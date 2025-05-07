package br.com.desafio.Wayon.code;

import br.com.desafio.Wayon.code.equalshashcode.Pessoa;
import br.com.desafio.Wayon.code.exemplo.adapter.EmailService;
import br.com.desafio.Wayon.code.exemplo.adapter.ThirdPartyEmailAdapter;
import br.com.desafio.Wayon.code.exemplo.adapter.ThirdPartyEmailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);

		// Criando a implementação concreta do ThirdPartyEmailSender
		ThirdPartyEmailSender sender = new ThirdPartyEmailSender() {
			@Override
			public void sendEmail(String to, String subject, String body) {
				// Implementação simulada ou real do serviço de e-mail
				System.out.println("Enviando e-mail para: " + to);
				System.out.println("Assunto: " + subject);
				System.out.println("Corpo: " + body);
			}
		};

		// Criando o adaptador com a implementação do sender
		EmailService emailService = new ThirdPartyEmailAdapter(sender);

		// Enviando o e-mail
		emailService.sendEmail("cliente@exemplo.com", "Bem-vindo", "Obrigado por se cadastrar!");

		Pessoa p1 =  new Pessoa("João", "123456789");
		Pessoa p2 =  new Pessoa("Maria", "123456789");

		System.out.println(p1.equals(p2)); // true, pois os CPFs são iguais

		Set<Pessoa> pessoas = new HashSet<>();
		pessoas.add(p1);
		pessoas.add(p2); // Não será adicionado, pois o CPF já existe no conjunto

		System.out.println(pessoas.size()); // 1, pois o CPF é a chave de comparação
	}

}
