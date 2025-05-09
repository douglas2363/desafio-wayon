package br.com.desafio.Wayon.code.cadastro.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UsuarioCadastroService {

    @Autowired
    private UsuarioCadastroRepository usuarioCadastroRepository;

    // Validação de e-mail
    private boolean isEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }

    // Inserir usuário
    public String inserirUsuario(UsuarioCadastro usuarioCadastro) {
        if (usuarioCadastro.getNome() == null || usuarioCadastro.getNome().isEmpty() ||
                usuarioCadastro.getEmail() == null || usuarioCadastro.getEmail().isEmpty()) {
            return "Erro: Nome e E-mail são obrigatórios!";
        }

        if (!isEmailValido(usuarioCadastro.getEmail())) {
            return "Erro: E-mail inválido";
        }

        // Verifica se o e-mail já está cadastrado
        if (usuarioCadastroRepository.existsByEmail(usuarioCadastro.getEmail())) {
            return "Erro: E-mail já cadastrado!";
        }

        usuarioCadastroRepository.save(usuarioCadastro);
        return "Usuário inserido com sucesso!";
    }

    // Atualizar usuário
    public String atualizarUsuario(UsuarioCadastro usuarioCadastro) {
        // Verificar se o usuário existe com base no ID
        Optional<UsuarioCadastro> usuarioExistente = usuarioCadastroRepository.findById(usuarioCadastro.getId());

        if (usuarioExistente.isEmpty()) {
            return "Erro: Usuário não encontrado!";
        }

        // Verificar se o e-mail foi alterado
        if (!usuarioExistente.get().getEmail().equals(usuarioCadastro.getEmail())) {
            // Verificar se o novo e-mail já está em uso por OUTRO usuário
            Optional<UsuarioCadastro> usuarioComMesmoEmail = usuarioCadastroRepository.findByEmail(usuarioCadastro.getEmail());
            if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(usuarioCadastro.getId())) {
                return "Erro: E-mail já cadastrado!";
            }
        }

        // Se tudo estiver ok, atualizar o usuário
        usuarioCadastroRepository.save(usuarioCadastro);
        return "Usuário atualizado com sucesso!";
    }

    // Excluir usuário (somente admin)
    public String excluirUsuario(UsuarioCadastro usuarioCadastroAdmin, String email) {
        if (usuarioCadastroAdmin == null || !usuarioCadastroAdmin.getEmail().equals("admin@admin.com")) {
            return "Erro: Somente administradores podem excluir usuários.";
        }

        // Verificando se o usuário existe
        Optional<UsuarioCadastro> usuarioExistente = usuarioCadastroRepository.findByEmail(email);
        if (usuarioExistente.isEmpty()) {
            return "Erro: Usuário não encontrado!";
        }

        usuarioCadastroRepository.deleteById(usuarioExistente.get().getId());
        return "Usuário excluído com sucesso!";
    }
}
