package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.services.ProfileService;
import br.com.backend.leitura_solidaria.services.UsersService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/profiles")
@Api(value = "API REST USER")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object findAll() {
        return service.findAll(mapper);
    }

}
