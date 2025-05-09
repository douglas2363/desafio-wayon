package br.com.desafio.Wayon.code.cadastro.usuario;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioCadastroController {

    @Autowired
    private UsuarioCadastroService usuarioCadastroService;

    // Inserir usuário
    @PostMapping
    public ResponseEntity<String> inserir(@Valid @RequestBody UsuarioCadastro usuarioCadastro) {
        return new ResponseEntity<>(usuarioCadastroService.inserirUsuario(usuarioCadastro), HttpStatus.CREATED);
    }

    // Atualizar usuário
    @PutMapping
    public ResponseEntity<String> atualizar(@Valid @RequestBody UsuarioCadastro usuarioCadastro) {
        return new ResponseEntity<>(usuarioCadastroService.atualizarUsuario(usuarioCadastro), HttpStatus.OK);
    }

    // Excluir usuário
    @DeleteMapping("/{email}")
    public ResponseEntity<String> excluir(@RequestBody UsuarioCadastro usuarioCadastroAdmin, @PathVariable String email) {
        return new ResponseEntity<>(usuarioCadastroService.excluirUsuario(usuarioCadastroAdmin, email), HttpStatus.OK);
    }
}
