package br.com.comandas.controller;

import br.com.comandas.domain.usuario.Usuario;
import br.com.comandas.domain.usuario.UsuarioRepository;
import br.com.comandas.domain.usuario.dto.CriaUsuarioDto;
import br.com.comandas.domain.usuario.dto.DetalheUsuarioDto;
import br.com.comandas.domain.usuario.dto.EditaUsuarioDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @Transactional()
    public ResponseEntity createUsuario(@RequestBody() CriaUsuarioDto dados) {
        Usuario usuario = new Usuario(dados);
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllUsuarios() {
        var usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editUsuario(@PathVariable Long id, @RequestBody() EditaUsuarioDto dados) {
        var usuarioEncontrado = repository.getReferenceById(id);

        if (usuarioEncontrado == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioEncontrado.update(dados);

        return ResponseEntity.ok(new DetalheUsuarioDto(usuarioEncontrado));
    }

    @DeleteMapping("/{id}")
    @Transactional()
    public ResponseEntity deleteUsuario(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

}
