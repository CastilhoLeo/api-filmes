package br.com.castilholeo.api_filmes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePesquisaFilme {

    private int page;
    private List<FilmeDTO> results;
    private int total_pages;
    private int total_results;
}
