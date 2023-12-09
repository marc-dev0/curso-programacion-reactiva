package com.step.verifier;

import com.step.verifier.servicios.ServicioSencillo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
class PruebasServiciosStepVerifierApplicationTests {

	@Autowired
	ServicioSencillo servicioSencillo;

	@Test
	void testMono() {
		Mono<String> uno = servicioSencillo.buscarUno();
		StepVerifier.create(uno)
				.expectNext("Hola")
				.verifyComplete();
	}

	@Test
	void testVarios() {
		Flux<String> varios = servicioSencillo.buscarTodos();
		StepVerifier.create(varios)
				.expectNext("Hola")
				.expectNext("mano")
				.expectNext("como")
				.expectNext("estas")
				.verifyComplete();
	}

	@Test
	void testVariosLento() {
		Flux<String> varios = servicioSencillo.buscarTodosLento();
		StepVerifier.create(varios)
				.expectNext("Hola")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("mano")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("como")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("estas")
				.thenAwait(Duration.ofSeconds(1))
				.verifyComplete();
	}
}
