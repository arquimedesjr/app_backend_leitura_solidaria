package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.request.PartnerRequest;
import br.com.backend.leitura_solidaria.domain.response.PartnerResponse;
import br.com.backend.leitura_solidaria.services.PartnerService;
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
@RequestMapping(value = "/v1/partner")
@Api(value = "API REST PARTNER")
@CrossOrigin(origins = "*")
public class PartnerController {

    @Autowired
    private PartnerService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object findAll() {
        return service.findAll(mapper);
    }

    @GetMapping(value = "/partial")
    @ResponseStatus(HttpStatus.OK)
    public Object findAllCodeName() {
        return service.findAllCodeName(mapper);
    }

    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Object find(@PathVariable Integer id) {
        return service.find(id, mapper);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object insert(@Valid @RequestBody PartnerRequest obj) {

        PartnerResponse users = service.insert(obj, mapper);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(users.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody PartnerRequest obj, @PathVariable Integer id) {
        service.update(obj, id, mapper);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping(value = {"/page"})
    @ResponseStatus(HttpStatus.OK)
    public Object findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                           @RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
                           @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

        return service.findPage(page, linesPerPage, orderBy, direction, mapper);
    }

}
