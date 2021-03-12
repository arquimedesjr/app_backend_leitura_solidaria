package br.com.backend.leitura_solidaria.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErro(String fielName, String messagem) {
        this.errors.add(new FieldMessage(fielName,messagem));
    }
}
