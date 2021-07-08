package br.com.backend.leitura_solidaria.domain.response.pagination;

import br.com.backend.leitura_solidaria.domain.response.UsersResponse;
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
public class UsersPaginationResponse {

    @JsonProperty("results")
    private List<UsersResponse> users;
    @JsonProperty("count")
    private Integer count;
}
