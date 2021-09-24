package com.bolsadeideas.springboot.webflux.app.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import com.bolsadeideas.springboot.webflux.app.models.dao.MutantDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Mutant;
import com.bolsadeideas.springboot.webflux.app.models.services.MutantService;
import com.bolsadeideas.springboot.webflux.app.models.services.MutantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;


@ExtendWith(SpringExtension.class)
@WebFluxTest(MutantController.class)
@Import(MutantService.class)
@AutoConfigureWebTestClient
@SpringBootTest
public class MuntantControllerTest {

    @Mock
    MutantDao dao;

    @Mock
    MutantServiceImpl service;

    private WebTestClient webClient = WebTestClient
            .bindToWebHandler(exchange -> exchange.getResponse().setComplete())
            .configureClient().build();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
   public void testCreateEmployee() {

        Mutant mutant = Mutant.builder()
                .id("1")
                .name("Luiskk")
                .createAt(new Date())
                .mutant(true)
                .build();

        Mockito.when(service.save(any())).thenReturn(Mono.just(mutant));
        Mockito.when(dao.save(any())).thenReturn(Mono.just(mutant));

        webClient.post()
                .uri("/mutant/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(mutant), Mutant.class)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void testFindMutant()
    {
        Mutant mutant = Mutant.builder()
                .id("1")
                .name("Luiskk")
                .createAt(new Date())
                .mutant(true)
                .build();
        Mockito.when(service.findById("1"))
                .thenReturn(Mono.just(mutant));

        webClient.get().uri("findById/{id}", "1")
                .exchange()
                .expectStatus().isOk();
                /*.expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("Luiskk")
                .jsonPath("$.mutant").isEqualTo(true);*/
    }

    @Test
    public void testDeleteMutant()
    {
        Mono<Void> voidReturn  = Mono.empty();
        Mockito.when(service.delete("1"))
                .thenReturn(voidReturn);

        webClient.get().uri("deleteById/{id}", "1")
                .exchange()
                .expectStatus().isOk();
    }



}
