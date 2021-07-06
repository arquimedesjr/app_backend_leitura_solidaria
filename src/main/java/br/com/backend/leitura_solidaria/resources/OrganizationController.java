package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.services.impl.OrganizationServiceImpl;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
