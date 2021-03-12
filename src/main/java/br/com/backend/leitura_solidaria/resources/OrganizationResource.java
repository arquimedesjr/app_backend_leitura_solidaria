package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Organization;
import br.com.backend.leitura_solidaria.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class OrganizationResource {

    @Autowired
    private OrganizationService service;

    @GetMapping(value = {"/organization"})
    public ResponseEntity<?> listOrganization() {
        List<Organization> obj = service.search();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = {"/organization/{id}"})
    public ResponseEntity<?> listOrganization(@PathVariable Integer id) {
        Organization obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/organization")
    public ResponseEntity<?> insertOrganization(@RequestBody Organization obj) {
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdOrganization()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
