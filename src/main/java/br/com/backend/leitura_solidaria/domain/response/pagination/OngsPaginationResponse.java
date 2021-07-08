package br.com.backend.leitura_solidaria.domain.response.pagination;

import br.com.backend.leitura_solidaria.domain.response.OngsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OngsPaginationResponse {

    @JsonProperty("results")
    private List<OngsResponse> ongs;
    @JsonProperty("count")
    private Integer count;
}
