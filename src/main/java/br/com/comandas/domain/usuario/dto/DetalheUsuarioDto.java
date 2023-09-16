package br.com.comandas.domain.usuario.dto;

import br.com.comandas.domain.usuario.Usuario;

public record DetalheUsuarioDto(String nome, String telefone, String email) {
    public DetalheUsuarioDto(Usuario usuarioEncontrado) {
        this(usuarioEncontrado.getNome(), usuarioEncontrado.getTelefone(), usuarioEncontrado.getEmail());
    }
}
