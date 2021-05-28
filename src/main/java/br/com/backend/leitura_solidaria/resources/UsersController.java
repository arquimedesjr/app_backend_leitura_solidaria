package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.services.UsersService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Type;

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

    @GetMapping(value = {"/page"})
    public Page<Object> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                 @RequestParam(name = "orderBy", defaultValue = "idUsers") String orderBy,
                                 @RequestParam(name = "direction", defaultValue = "ASC") String direction) {


        return mapper.map(service.findPage(page, linesPerPage, orderBy, direction), (Type) Users.class);
    }


    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Object find(@PathVariable Integer id) {
        return mapper.map(service.find(id, mapper), Users.class);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Object insert(@Valid @RequestBody Users obj) {

        UsersEntity obEntity = mapper.map(obj, UsersEntity.class);
        obj = mapper.map(service.insert(obEntity), Users.class);

        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Users obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(mapper.map(obj, UsersEntity.class));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}
