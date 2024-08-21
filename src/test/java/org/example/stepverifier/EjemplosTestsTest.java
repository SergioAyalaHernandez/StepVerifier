package org.example.stepverifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class EjemplosTestsTest {

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
}