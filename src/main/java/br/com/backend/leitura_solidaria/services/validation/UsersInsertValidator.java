package br.com.backend.leitura_solidaria.services.validation;

import br.com.backend.leitura_solidaria.domain.response.Users;
import br.com.backend.leitura_solidaria.exception.FieldMessage;
import br.com.backend.leitura_solidaria.models.entity.UsersEntity;
import br.com.backend.leitura_solidaria.models.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UsersInsertValidator implements ConstraintValidator<UsersInsert, Users> {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private HttpServletRequest servletRequest;

    @Override
    public void initialize(UsersInsert ann) {
    }

    @Override
    public boolean isValid(Users obj, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        UsersEntity aux = usersRepository.findByMail(obj.getMail());

        if (aux != null && !aux.getId().equals(uriId))
            list.add(new FieldMessage("mail", "Email já existente"));


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
