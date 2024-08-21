package org.example.stepverifier.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class MonoServices {

    public Mono<String> buscarUno(){
        return Mono.just("Hola");
    }

    public Flux<String> buscarTodos(){
        return Flux.just("hola","que","tal");
    }

    public Flux<String> buscarTodosLento(){
        return Flux.just("hola","que","tal")
                .delaySequence(Duration.ofSeconds(10));
    }

}
