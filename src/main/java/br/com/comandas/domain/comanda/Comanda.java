package br.com.comandas.domain.comanda;

import br.com.comandas.domain.comanda.item.Item;
import br.com.comandas.domain.dto.AtualizaComandaDto;
import br.com.comandas.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "tb_comandas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> itens;

    private boolean aberta;

    public Comanda(Usuario usuario) {
        this.usuario = usuario;
        this.aberta = true;
    }

    public void update(AtualizaComandaDto dados) {
        if(dados.aberta() != null){
            this.aberta = dados.aberta();
        }
        if(dados.usuario() != null){
            this.usuario = dados.usuario();
        }
    }
}
