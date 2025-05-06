package br.com.desafio.Wayon.code.anagram.equalshashcode;

import java.util.Objects;

public class Pessoa {
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    //Sobreescrevendo o método equals
    @Override
    public boolean equals(Object obj) {
        // Verifica se são o mesmo objeto que está em memória.
        if (this == obj) return true;

        // Verifica se o objeto é nulo ou se as classes são diferentes.
        if (obj == null || getClass() != obj.getClass()) return false;

        // Compara CPFs
        Pessoa outra = (Pessoa) obj;
        if (cpf == null) {
            return outra.cpf == null;
        } else {
            return cpf.equals(outra.cpf);
        }
    }

    // Sobrescrevendo hashCode() sem usar Objects.hash()
    // Isso é importante para manter o contrato entre equals() e hashCode()
    // Se dois objetos são "iguais" com base no equals(), eles devem ter o mesmo hashCode
    @Override
    public int hashCode() {
        // Se o CPF não for nulo, retorna o hashCode do próprio CPF (String já implementa isso)
        // Caso o CPF seja nulo, retorna 0 para evitar NullPointerException
        return cpf != null ? cpf.hashCode() : 0;
    }

}
