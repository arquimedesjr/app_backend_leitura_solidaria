package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.dto.OrganizationDTO;
import br.com.backend.leitura_solidaria.services.OrganizationService;
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
@RequestMapping(value = "/organization")
@Api(value = "API REST ORGANIZATION")
@CrossOrigin(origins = "*")
public class OrganizationResource {

    @Autowired
    private OrganizationService service;

    @GetMapping()
    public ResponseEntity<List<Organization>> findAll() {
        List<Organization> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/page"})
    public ResponseEntity<Page<Organization>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                   @RequestParam(name = "orderBy", defaultValue = "idOrganization") String orderBy,
                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Page<Organization> list = service.findPage(page,linesPerPage,orderBy,direction);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Organization> find(@PathVariable Integer id) {
        Organization obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping()
    public ResponseEntity<Organization> insert(@Valid @RequestBody OrganizationDTO objDTO) {
        Organization obj = service.fromDTO(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Organization> update(@Valid @RequestBody Organization obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Organization> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
