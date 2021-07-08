package br.com.backend.leitura_solidaria.domain.response.pagination;

import br.com.backend.leitura_solidaria.domain.response.OngsUserResponse;
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
    private List<OngsUserResponse> ongs;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("total_elements")
    private Long totalElements;
    @JsonProperty("total_pages")
    private Integer totalPages;
}
