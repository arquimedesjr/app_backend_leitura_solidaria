package br.com.backend.leitura_solidaria.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandarError implements Serializable {

    private Integer status;
    private String msg;
    private Long timeStamp;

}
