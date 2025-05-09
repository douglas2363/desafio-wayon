package br.com.desafio.Wayon.code.cadastro.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class UsuarioCadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(unique = true)
    private String email;

    private String endereco;
    private String telefone;

    public UsuarioCadastro() {}

    public UsuarioCadastro(String nome, String email, String endereco, String telefone) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "Nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "E-mail é obrigatório") @Email(message = "E-mail inválido") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "E-mail é obrigatório") @Email(message = "E-mail inválido") String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
