package br.com.castilholeo.api_filmes.service.external;

import br.com.castilholeo.api_filmes.dto.FilmeDTO;
import br.com.castilholeo.api_filmes.dto.ResponsePesquisaFilme;
import br.com.castilholeo.api_filmes.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ExternalApiService {

    private final WebClient webClient;

    public ExternalApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<FilmeDTO> localizarFilmePeloNome(String filme) {

        List<FilmeDTO> lista= webClient.get()
                .uri(String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=pt-BR&page=1", filme))
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTUzNDM3ODU3YzEyMmI5NGRhZGIwNTYwYTZjZjk2ZSIsIm5iZiI6MTcyNTQ5NzIxOC4yMDA3OTksInN1YiI6IjY2ZDhmZTFiYjFlZmIwZGNlYTlkOGVlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4HvyWGpjRxo2jkSIK8M-VmyUAFJjkpDoLWCH4TEKn7I")
                .retrieve()
                .bodyToMono(ResponsePesquisaFilme.class).map(obj-> obj.getResults().stream().toList())
                .block();

        return lista;
    }

    public List<FilmeDTO> localizarFilmeSugestao(int id) {

            List<FilmeDTO> lista= webClient.get()
                    .uri(String.format("https://api.themoviedb.org/3/movie/%d/recommendations?language=pt-BR&page=1", id))
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTUzNDM3ODU3YzEyMmI5NGRhZGIwNTYwYTZjZjk2ZSIsIm5iZiI6MTcyNTQ5NzIxOC4yMDA3OTksInN1YiI6IjY2ZDhmZTFiYjFlZmIwZGNlYTlkOGVlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4HvyWGpjRxo2jkSIK8M-VmyUAFJjkpDoLWCH4TEKn7I")
                    .retrieve()
                    .bodyToMono(ResponsePesquisaFilme.class).map(obj-> obj.getResults().stream().toList())
                    .block();

            return lista;
        }

    public FilmeDTO localizarFilmePeloId(int id) {

        FilmeDTO filme = webClient.get()
                .uri(String.format("https://api.themoviedb.org/3/movie/%d?language=pt-BR", id))
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTUzNDM3ODU3YzEyMmI5NGRhZGIwNTYwYTZjZjk2ZSIsIm5iZiI6MTcyNTQ5NzIxOC4yMDA3OTksInN1YiI6IjY2ZDhmZTFiYjFlZmIwZGNlYTlkOGVlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4HvyWGpjRxo2jkSIK8M-VmyUAFJjkpDoLWCH4TEKn7I")
                .retrieve()
                .bodyToMono(FilmeDTO.class)
                .block();

        return filme;
    }
}
