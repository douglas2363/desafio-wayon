package br.com.desafio.Wayon.code.seguranca.orm;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    // Consulta segura e parametrizada
    Optional<Usuario> findByEmail(String email);
    // Este método cria uma consulta baseada no nome do método. O Spring Data JPA automaticamente cria a consulta para buscar um Usuário
    // com o email passado como parâmetro. O 'Optional' é usado para indicar que pode não haver um usuário com o email informado.

    // Consulta insegura
    boolean existsByEmail(String email);
    // Este método verifica se existe um usuário com o email informado. Ele retorna um booleano indicando se o usuário foi encontrado.
    // Essa consulta pode não ser segura, pois pode ser vulnerável a SQL Injection se não for parametrizada corretamente.

    // Consulta utilizando a anotação @Query
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmailWithQuery(@Param("email") String email);


    // Atualizando a senha de um usuário
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.senha = :senha WHERE u.email = :email")
    void updateSenha(@Param("email") String email, @Param("senha") String senha);

}

