package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {

    @Autowired
    private UsersService service;

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> listUsers(@PathVariable Integer id) {
        Users obj = service.search(id);
        return ResponseEntity.ok().body(obj);
    }
}
