package org.example.stepverifier;

import org.example.stepverifier.services.MonoServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EjemplosTestsTest {

    @Autowired
    MonoServices monoServices;

    @Test
    void testFlux() {
        Flux<Integer> fluxToTest = Flux.just(1,2,3,4,5,6,7);
        StepVerifier.create(fluxToTest)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectNext(6)
                .expectNext(7)
                .expectComplete()
                .verify();
    }
    @Test
    public void testFluxString(){
        Flux<String> fluxToTest = Flux.just("Sergio","Linda","Claudia","jose","josea")
                .filter(nombre -> nombre.length() <= 5)
                .map(String::toUpperCase);

        StepVerifier.create(fluxToTest)
                .expectNext("LINDA")
                .expectNext("JOSE")
                .expectNextMatches(nombre -> nombre.startsWith("J"))
                .expectComplete()
                .verify();

    }

    @Test
    void testMono(){
        Mono<String> uno = monoServices.buscarUno();
        StepVerifier.create(uno).expectNext("Hola").verifyComplete();
    }

    @Test
    void testVarios(){
        Flux<String> varios = monoServices.buscarTodos();
        StepVerifier.create(varios).expectNext("hola").expectNext("que").expectNext("tal").verifyComplete();
    }

    @Test
    void testTiempos(){
        Flux<String> varios = monoServices.buscarTodosLento();
        StepVerifier.create(varios).expectNext("hola")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("que")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("tal")
                .thenAwait(Duration.ofSeconds(1))
                .verifyComplete();
    }
}