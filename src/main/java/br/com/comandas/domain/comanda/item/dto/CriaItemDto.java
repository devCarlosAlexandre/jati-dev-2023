package br.com.comandas.domain.comanda.item.dto;

import java.math.BigDecimal;

public record CriaItemDto(String nome, Integer quantidade, BigDecimal preco_item) {
}
