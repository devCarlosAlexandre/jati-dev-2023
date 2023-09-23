package br.com.comandas.controller;

import br.com.comandas.domain.comanda.Comanda;
import br.com.comandas.domain.comanda.ComandaRepository;
import br.com.comandas.domain.dto.AtualizaComandaDto;
import br.com.comandas.domain.dto.CriaComandaDto;
import br.com.comandas.domain.dto.DetalheComandaDto;
import br.com.comandas.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/comandas")
public class ComandaController {
    @Autowired
    ComandaRepository comandaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping()
    @Transactional()
    public ResponseEntity createComanda(@RequestBody() CriaComandaDto dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioRepository.getReferenceById(dados.usuario_id());

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        Comanda comanda = new Comanda(usuario);
        comandaRepository.save(comanda);
        var uri = uriBuilder.path("/comandas/{id}").buildAndExpand(comanda.getId()).toUri();

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @Transactional()
    public ResponseEntity getAllComandas() {
        var comandas = comandaRepository.findAllByAbertaTrue();
        var comandasDetalhada = comandas.stream().map(DetalheComandaDto::new);

        return ResponseEntity.ok(comandasDetalhada);
    }

    @PutMapping("/{id}")
    @Transactional()
    public ResponseEntity updateComanda(@PathVariable Long id, @RequestBody AtualizaComandaDto dados) {
        var comanda = comandaRepository.getReferenceById(id);

        if(comanda == null){
            return ResponseEntity.notFound().build();
        }

        comanda.update(dados);

        return ResponseEntity.ok(new DetalheComandaDto(comanda));
    }


    @GetMapping("/{id}")
    @Transactional()
    public ResponseEntity getComandaId(@PathVariable() Long id) {
        var comanda = comandaRepository.getReferenceById(id);

        if (comanda == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetalheComandaDto(comanda));

    }
}
