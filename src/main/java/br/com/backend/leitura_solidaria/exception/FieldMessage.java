package br.com.backend.leitura_solidaria.exception;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;

}

