package br.com.castilholeo.api_filmes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PesquisaFilmeDTO {

    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids = new ArrayList<>();
    private int id;
    private String poster_path;
    private String title;

}
