package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UsersResource {

    @Autowired
    private UsersService service;

    @GetMapping(value = {"/users"})
    public ResponseEntity<?> listUsers() {
        List<Users> obj = service.search();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = {"/users/{id}"})
    public ResponseEntity<?> listUsers(@PathVariable Integer id) {
        Users obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/users")
    public ResponseEntity<?> insertUsers(@RequestBody Users obj) {
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdUsers()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
