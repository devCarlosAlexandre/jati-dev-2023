package br.com.comandas.domain.comanda.item;

import br.com.comandas.domain.comanda.Comanda;
import br.com.comandas.domain.comanda.item.dto.CriaItemDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "tb_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comanda_id")
    private Comanda comanda;

    @Column(precision = 13, scale = 2)
    private BigDecimal preco_item;

    public Item(Comanda comanda, CriaItemDto dados) {
        this.comanda = comanda;
        this.preco_item =dados.preco_item();
        this.quantidade = dados.quantidade();
        this.nome = dados.nome();
    }
}
