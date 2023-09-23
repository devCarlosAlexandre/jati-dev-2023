package br.com.comandas.domain.dto;

import br.com.comandas.domain.comanda.Comanda;
import br.com.comandas.domain.comanda.item.dto.DetalheItemDto;
import br.com.comandas.domain.usuario.Usuario;

import java.util.stream.Stream;

public record DetalheComandaDto(Long id, Usuario usuario, Boolean aberta, Stream<DetalheItemDto> itens) {

    public DetalheComandaDto(Comanda comanda){
    this(comanda.getId(), comanda.getUsuario(), comanda.isAberta(), comanda.getItens().stream().map(DetalheItemDto::new));
    }
}
