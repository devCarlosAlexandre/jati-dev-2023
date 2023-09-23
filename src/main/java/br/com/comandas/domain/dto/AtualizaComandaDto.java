package br.com.comandas.domain.dto;

import br.com.comandas.domain.usuario.Usuario;

public record AtualizaComandaDto(Usuario usuario, Boolean aberta) {
}
