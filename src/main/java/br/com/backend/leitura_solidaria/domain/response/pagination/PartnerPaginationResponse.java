package br.com.backend.leitura_solidaria.domain.response.pagination;

import br.com.backend.leitura_solidaria.domain.response.PartnerResponse;
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
public class PartnerPaginationResponse {

    @JsonProperty("results")
    private List<PartnerResponse> partner;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("total_elements")
    private Long totalElements;
    @JsonProperty("total_pages")
    private Integer totalPages;
}
