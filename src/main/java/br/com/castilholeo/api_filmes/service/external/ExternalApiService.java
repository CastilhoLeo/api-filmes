package br.com.castilholeo.api_filmes.service.external;

import br.com.castilholeo.api_filmes.dto.FilmeDTO;
import br.com.castilholeo.api_filmes.dto.ResponsePesquisaFilme;
import br.com.castilholeo.api_filmes.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class ExternalApiService {

    @Value("${Barier}")
    private String barier;

    private final WebClient webClient;

    public ExternalApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<FilmeDTO> localizarFilmePeloNome(String filme) {

        List<FilmeDTO> lista= webClient.get()
                .uri(String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=pt-BR&page=1", filme))
                .header("Authorization", barier)
                .retrieve()
                .bodyToMono(ResponsePesquisaFilme.class).map(obj-> obj.getResults().stream().toList())
                .block();

        return lista;
    }

    public List<FilmeDTO> localizarFilmeSugestao(int id) {

            List<FilmeDTO> lista= webClient.get()
                    .uri(String.format("https://api.themoviedb.org/3/movie/%d/recommendations?language=pt-BR&page=1", id))
                    .header("Authorization", barier)
                    .retrieve()
                    .bodyToMono(ResponsePesquisaFilme.class).map(obj-> obj.getResults().stream().toList())
                    .block();

            return lista;
        }

    public FilmeDTO localizarFilmePeloId(int id) {

        FilmeDTO filme = webClient.get()
                .uri(String.format("https://api.themoviedb.org/3/movie/%d?language=pt-BR", id))
                .header("Authorization", barier)
                .retrieve()
                .bodyToMono(FilmeDTO.class)
                .block();

        return filme;
    }
}
