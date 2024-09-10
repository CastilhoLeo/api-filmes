package br.com.castilholeo.api_filmes.service;

import br.com.castilholeo.api_filmes.dto.FilmeDTO;
import br.com.castilholeo.api_filmes.service.external.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private ExternalApiService externalApiService;

    public List<FilmeDTO> resultadoPesquisaFilme(String filme){
        return externalApiService.localizarFilmePeloNome(filme);
    }

    public List<FilmeDTO> resultadoPesquisaSugestao(int id){

        return externalApiService.localizarFilmeSugestao(id);
    }


}
