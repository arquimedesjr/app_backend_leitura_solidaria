package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.dto.UsersDTO;
import br.com.backend.leitura_solidaria.services.UsersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@Api(value = "API REST USER")
@CrossOrigin(origins = "*")
public class UsersResource {

    @Autowired
    private UsersService service;

    @GetMapping()
    public ResponseEntity<List<UsersDTO>> findAll() {
        List<Users> list = service.findAll();
        List<UsersDTO> dtoList = list.stream().map(UsersDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = {"/page"})
    public ResponseEntity<Page<UsersDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                   @RequestParam(name = "orderBy", defaultValue = "idUsers") String orderBy,
                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Users> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<UsersDTO> dtoList = list.map(UsersDTO::new);
        return ResponseEntity.ok().body(dtoList);
    }


    @GetMapping(value = {"/{id}"})
    public ResponseEntity<UsersDTO> find(@PathVariable Integer id) {
        Users obj = service.find(id);
        UsersDTO objDto = new UsersDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @PostMapping()
    public ResponseEntity<Users> insert(@Valid @RequestBody Users obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Users> update(@Valid @RequestBody Users obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Users> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
