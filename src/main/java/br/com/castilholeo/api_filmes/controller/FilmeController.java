package br.com.castilholeo.api_filmes.controller;

import br.com.castilholeo.api_filmes.dto.FilmeDTO;
import br.com.castilholeo.api_filmes.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/filme")
    ResponseEntity<List<FilmeDTO>> localizarFilmePeloNome(
            @RequestParam (value = "filme", required = false) String filme){

        return ResponseEntity.ok().body(filmeService.resultadoPesquisaFilme(filme));
    }

    @GetMapping("/sugestao/{id}")
    ResponseEntity<List<FilmeDTO>> localizarSugestoes(
            @PathVariable("id") int id
            ){

        return ResponseEntity.ok().body(filmeService.resultadoPesquisaSugestao(id));
    }

}
