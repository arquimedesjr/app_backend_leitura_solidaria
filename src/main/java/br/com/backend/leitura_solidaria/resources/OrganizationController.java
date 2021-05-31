package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.response.Organization;
import br.com.backend.leitura_solidaria.models.entity.OrganizationEntity;
import br.com.backend.leitura_solidaria.services.impl.OrganizationServiceImpl;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Type;

@RestController
@RequestMapping(value = "/v1/organization")
@Api(value = "API REST ORGANIZATION")
@CrossOrigin(origins = "*")
public class OrganizationController {

    @Autowired
    private OrganizationServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Object findAll() {
        return service.findAll();
    }

    @GetMapping(value = {"/page"})
    @ResponseStatus(HttpStatus.OK)
    public Object findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                           @RequestParam(name = "orderBy", defaultValue = "idOrganization") String orderBy,
                           @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        return service.findPage(page, linesPerPage, orderBy, direction);
    }

    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Object find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Object insert(@Valid @RequestBody Organization obj) {

        obj = mapper.map(obj, (Type) OrganizationEntity.class);

        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Organization obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(mapper.map(obj, OrganizationEntity.class));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}
