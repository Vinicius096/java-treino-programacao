package br.com.coruja.application.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.coruja.domain.model.AlunoModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AplicationControllerTest {
    @Autowired
    protected WebTestClient web;

    private AlunoModel aluno;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
        aluno = new AlunoModel();
        aluno.setID(1);
        aluno.setNome("Nome Sobrenome");
        aluno.setEmail("nome.sobrenome@email.com");
    }

    @Test
    public void salvar_novo_aluno(){
        web.post().uri("/alunos")
        .body(BodyInserters.fromValue(aluno))
        .accept(MediaType.APPLICATION_JSON)
        .exchange().expectStatus().isCreated()
        .expectBody().json(
            "{\"nome\": \"Nome Sobrenome\",\"email\": \"nome.sobrenome@email.com\",\"id\": 1}"
        );
    }
    
}
