package br.com.backend.leitura_solidaria.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private String type;

}
