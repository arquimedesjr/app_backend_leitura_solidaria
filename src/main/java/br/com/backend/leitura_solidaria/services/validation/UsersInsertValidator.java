package br.com.backend.leitura_solidaria.services.validation;

import br.com.backend.leitura_solidaria.domain.Users;
import br.com.backend.leitura_solidaria.dto.UsersDTO;
import br.com.backend.leitura_solidaria.repositories.UsersRepository;
import br.com.backend.leitura_solidaria.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UsersInsertValidator implements ConstraintValidator<UsersInsert, UsersDTO> {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public void initialize(UsersInsert ann) {
    }

    @Override
    public boolean isValid(UsersDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Users aux = usersRepository.findByMail(objDto.getMail());
        if (aux != null)
            list.add(new FieldMessage("mail", "Email já existente"));


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
