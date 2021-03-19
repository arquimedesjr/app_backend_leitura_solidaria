package br.com.backend.leitura_solidaria.services.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsersInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsersInsert {
    String message() default "Erro de Validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
