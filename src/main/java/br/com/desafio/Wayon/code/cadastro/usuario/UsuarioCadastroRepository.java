package br.com.desafio.Wayon.code.cadastro.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioCadastroRepository extends JpaRepository<UsuarioCadastro, Long> {

    // Buscar por e-mail (garante que o e-mail será único)
    Optional<UsuarioCadastro> findByEmail(String email);

    // Verificar se já existe um usuário com o e-mail
    boolean existsByEmail(String email);
}
