package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.request.OrganizationRequest;
import br.com.backend.leitura_solidaria.domain.request.UsersRequest;
import br.com.backend.leitura_solidaria.domain.response.OrganizationResponse;
import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
import br.com.backend.leitura_solidaria.services.impl.OrganizationServiceImpl;
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
        return service.findAll(mapper);
    }

    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Object find(@PathVariable Integer id) {
        return service.find(id, mapper);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object insert(@Valid @RequestBody OrganizationRequest obj) {

        OrganizationResponse organizationResponse = service.insert(obj, mapper);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(organizationResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


}
