package br.com.backend.leitura_solidaria.resources;

import br.com.backend.leitura_solidaria.domain.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Users> listUsers() {

        Users users = new Users(1, "Arquimedes Junior", "arquim@mail.com", "123", "https/tteses");
        Users users2 = new Users(2, "Arquimedes Junior", "arquim@mail.com", "123", "https/tteses");

        List<Users> list = new ArrayList<>();

        list.add(users);
        list.add(users2);

        return list;
    }
}
