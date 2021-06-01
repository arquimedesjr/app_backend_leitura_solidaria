package br.com.backend.leitura_solidaria.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private Integer id;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String cep;

}
