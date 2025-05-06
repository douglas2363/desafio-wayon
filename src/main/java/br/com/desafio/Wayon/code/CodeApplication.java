package br.com.desafio.Wayon.code;

import br.com.desafio.Wayon.code.anagram.equalshashcode.Pessoa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);

		Pessoa p1 =  new Pessoa("João", "123456789");
		Pessoa p2 =  new Pessoa("Maria", "123456789");

		System.out.println(p1.equals(p2)); // true, pois os CPFs são iguais

		Set<Pessoa> pessoas = new HashSet<>();
		pessoas.add(p1);
		pessoas.add(p2); // Não será adicionado, pois o CPF já existe no conjunto

		System.out.println(pessoas.size()); // 1, pois o CPF é a chave de comparação
	}

}
