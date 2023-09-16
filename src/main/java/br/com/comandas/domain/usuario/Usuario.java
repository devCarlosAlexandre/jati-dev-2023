package br.com.comandas.domain.usuario;

import br.com.comandas.domain.usuario.dto.CriaUsuarioDto;
import br.com.comandas.domain.usuario.dto.EditaUsuarioDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "tb_usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;


    public Usuario(CriaUsuarioDto dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
    }

    public void update(EditaUsuarioDto dados) {
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }

    }
}
