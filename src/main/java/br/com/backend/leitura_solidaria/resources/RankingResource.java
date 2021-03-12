package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Ranking;
import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.services.RankingService;
import br.com.backend.leitura_solidaria.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RankingResource {

    @Autowired
    private RankingService service;

    @GetMapping(value = {"/ranking"})
    public ResponseEntity<?> listUsers() {
        List<Ranking> obj = service.search();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = {"/ranking/{id}"})
    public ResponseEntity<?> listUsers(@PathVariable Integer id) {
        Ranking obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/ranking")
    public ResponseEntity<?> insertUsers(@RequestBody Ranking obj) {
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdRanking()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
