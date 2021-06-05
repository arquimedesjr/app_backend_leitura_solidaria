package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.Users;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.services.UsersService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/v1/users")
@Api(value = "API REST USER")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UsersService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Object findAll() {
        return service.findAll(mapper);
    }

    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Object find(@PathVariable Integer id) {
        return mapper.map(service.find(id, mapper), Users.class);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object insert(@Valid @RequestBody UsersRequest obj) {

        Users users = service.insert(obj, mapper);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(users.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UsersRequest obj, @PathVariable Integer id) {
        service.update(obj, id);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
