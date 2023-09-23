package br.com.comandas.domain.comanda.item.dto;

import br.com.comandas.domain.comanda.item.Item;

import java.math.BigDecimal;

public record DetalheItemDto(Long id, String nome, Integer quantidade, BigDecimal precoItem, BigDecimal total_item) {
    public DetalheItemDto(Item item) {
        this(item.getId(), item.getNome(), item.getQuantidade(), item.getPreco_item(), (item.getPreco_item().multiply(BigDecimal.valueOf(item.getQuantidade()))) );
    }
}
