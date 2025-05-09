package br.com.desafio.Wayon.code.cadastro.usuario.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.desafio.Wayon.code.cadastro.usuario.UsuarioCadastro;
import br.com.desafio.Wayon.code.cadastro.usuario.UsuarioCadastroRepository;
import br.com.desafio.Wayon.code.cadastro.usuario.UsuarioCadastroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

public class UsuarioCadastroServiceTest {
    @Mock
    private UsuarioCadastroRepository usuarioCadastroRepository;

    @InjectMocks
    private UsuarioCadastroService usuarioCadastroService;

    private UsuarioCadastro usuarioCadastroValido;
    private UsuarioCadastro usuarioCadastroInvalidoEmail;
    private UsuarioCadastro usuarioCadastroAdmin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criar usuários de teste
        usuarioCadastroValido = new UsuarioCadastro("João Silva", "joao@exemplo.com", "Rua 1, 123", "123456789");
        usuarioCadastroInvalidoEmail = new UsuarioCadastro("Carlos Pereira", "joao@exemplo.com", "Rua 2, 456", "987654321");
        usuarioCadastroAdmin = new UsuarioCadastro("Administrador", "admin@admin.com", "Admin Street", "111222333");
    }

    // 1° Cenário de Teste: Inserção de Usuário Válido
    @Test
    void testInserirUsuarioValido() {
        when(usuarioCadastroRepository.existsByEmail(usuarioCadastroValido.getEmail())).thenReturn(false);
        when(usuarioCadastroRepository.save(usuarioCadastroValido)).thenReturn(usuarioCadastroValido);

        String resultado = usuarioCadastroService.inserirUsuario(usuarioCadastroValido);

        assertEquals("Usuário inserido com sucesso!", resultado);
        verify(usuarioCadastroRepository, times(1)).save(usuarioCadastroValido);
    }

    // 2° Cenário de Teste: Inserção de Usuário com Dados Obrigatórios
    @Test
    void testInserirUsuarioComDadosObrigatorios() {
        UsuarioCadastro usuarioCadastroComDadosObrigatorios = new UsuarioCadastro("Lucas Lima", "lucas@exemplo.com", null, null);

        when(usuarioCadastroRepository.existsByEmail(usuarioCadastroComDadosObrigatorios.getEmail())).thenReturn(false);
        when(usuarioCadastroRepository.save(usuarioCadastroComDadosObrigatorios)).thenReturn(usuarioCadastroComDadosObrigatorios);

        String resultado = usuarioCadastroService.inserirUsuario(usuarioCadastroComDadosObrigatorios);

        assertEquals("Usuário inserido com sucesso!", resultado);
    }

    // 3° Cenário de Teste: Atualização de Dados do Usuário
    @Test
    void testAtualizarUsuario() {
        // Garantir que o ID do usuário seja não nulo
        usuarioCadastroValido.setId(1L);

        // Simulando que o usuário existe no repositório
        when(usuarioCadastroRepository.findById(usuarioCadastroValido.getId())).thenReturn(Optional.of(usuarioCadastroValido));
        when(usuarioCadastroRepository.save(usuarioCadastroValido)).thenReturn(usuarioCadastroValido);

        // Alterando dados do usuário
        usuarioCadastroValido.setEndereco("Rua 3, 999");

        String resultado = usuarioCadastroService.atualizarUsuario(usuarioCadastroValido);

        assertEquals("Usuário atualizado com sucesso!", resultado);
        verify(usuarioCadastroRepository, times(1)).save(usuarioCadastroValido);
    }

    // 4° Cenário de Teste: Atualizar e-mail (com validação de unicidade)
    @Test
    void testAtualizarEmailUnico() {
        // Configuração inicial
        UsuarioCadastro usuarioOriginal = new UsuarioCadastro("João Silva", "joao@exemplo.com", "Rua 1, 123", "123456789");
        usuarioOriginal.setId(1L);

        // Outro usuário que já tem o email que queremos usar
        UsuarioCadastro outroUsuario = new UsuarioCadastro("Maria Silva", "maria@exemplo.com", "Rua 2, 456", "987654321");
        outroUsuario.setId(2L);

        // Mock do repositório
        when(usuarioCadastroRepository.findById(1L)).thenReturn(Optional.of(usuarioOriginal));
        when(usuarioCadastroRepository.findByEmail("maria@exemplo.com")).thenReturn(Optional.of(outroUsuario));

        // Tentativa de atualização para um email que já existe
        UsuarioCadastro atualizacao = new UsuarioCadastro("João Silva", "maria@exemplo.com", "Rua 1, 123", "123456789");
        atualizacao.setId(1L);

        String resultado = usuarioCadastroService.atualizarUsuario(atualizacao);

        assertEquals("Erro: E-mail já cadastrado!", resultado);
        verify(usuarioCadastroRepository, never()).save(any());
    }

    // 5° Cenário de Teste: Exclusão de Usuário
    @Test
    void testExcluirUsuarioAdmin() {
        when(usuarioCadastroRepository.findByEmail("joao@exemplo.com")).thenReturn(java.util.Optional.of(usuarioCadastroValido));
        when(usuarioCadastroRepository.existsByEmail("joao@exemplo.com")).thenReturn(true);

        String resultado = usuarioCadastroService.excluirUsuario(usuarioCadastroAdmin, "joao@exemplo.com");

        assertEquals("Usuário excluído com sucesso!", resultado);
        verify(usuarioCadastroRepository, times(1)).deleteById(usuarioCadastroValido.getId());
    }

    // 6° Cenário de Teste: Exclusão de Usuário sem ser Admin
    @Test
    void testExcluirUsuarioNaoAdmin() {
        String resultado = usuarioCadastroService.excluirUsuario(new UsuarioCadastro("Ana", "ana@exemplo.com", "Rua 5, 777", "123987654"), "joao@exemplo.com");

        assertEquals("Erro: Somente administradores podem excluir usuários.", resultado);
        verify(usuarioCadastroRepository, times(0)).deleteById(any());
    }

    // 7° Cenário de Teste: Validação de Campos - Nome vazio
    @Test
    void testInserirUsuarioSemNome() {
        UsuarioCadastro usuarioCadastroSemNome = new UsuarioCadastro("", "novo@exemplo.com", "Rua 7, 101", "123456789");

        String resultado = usuarioCadastroService.inserirUsuario(usuarioCadastroSemNome);

        assertEquals("Erro: Nome e E-mail são obrigatórios!", resultado);
    }

    // 8° Cenário de Teste: Validação de Campos - E-mail vazio
    @Test
    void testInserirUsuarioSemEmail() {
        UsuarioCadastro usuarioCadastroSemEmail = new UsuarioCadastro("Lucas", "", "Rua 8, 222", "123123123");

        String resultado = usuarioCadastroService.inserirUsuario(usuarioCadastroSemEmail);

        assertEquals("Erro: Nome e E-mail são obrigatórios!", resultado);
    }

    // 9° Cenário de Teste: Validação de Campos - E-mail inválido
    @Test
    void testInserirUsuarioEmailInvalido() {
        UsuarioCadastro usuarioCadastroEmailInvalido = new UsuarioCadastro("Fernando", "emailinvalido", "Rua 9, 333", "123123123");

        String resultado = usuarioCadastroService.inserirUsuario(usuarioCadastroEmailInvalido);

        assertEquals("Erro: E-mail inválido", resultado);
    }

    // 10° Cenário de Teste: E-mail duplicado
    @Test
    void testInserirUsuarioEmailDuplicado() {
        when(usuarioCadastroRepository.existsByEmail(usuarioCadastroInvalidoEmail.getEmail())).thenReturn(true);

        String resultado = usuarioCadastroService.inserirUsuario(usuarioCadastroInvalidoEmail);

        assertEquals("Erro: E-mail já cadastrado!", resultado);
        verify(usuarioCadastroRepository, times(0)).save(usuarioCadastroInvalidoEmail);
    }

    @Test
    void testAtualizarUsuarioSemMudarEmail() {
        UsuarioCadastro usuario = new UsuarioCadastro("João Silva", "joao@exemplo.com", "Rua 1, 123", "123456789");
        usuario.setId(1L);

        when(usuarioCadastroRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Atualizando apenas o endereço, mantendo o mesmo email
        UsuarioCadastro atualizacao = new UsuarioCadastro("João Silva", "joao@exemplo.com", "Nova Rua, 456", "123456789");
        atualizacao.setId(1L);

        String resultado = usuarioCadastroService.atualizarUsuario(atualizacao);

        assertEquals("Usuário atualizado com sucesso!", resultado);
        verify(usuarioCadastroRepository, times(1)).save(atualizacao);
    }

}
