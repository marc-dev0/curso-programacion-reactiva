import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemploTests {
    @Test
    public void testFlux() {
        Flux<Integer> fluxToTest = Flux.just(1, 2, 3, 4 , 5);
        //Crear prueba
        StepVerifier.create(fluxToTest)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    @Test
    public void testFluxString() {
        Flux<String> fluxToText = Flux.just("Jessica", "Tomas", "Mellisa", "John", "Lia", "Thua")
                .filter(x -> x.length() <= 5)
                .map(String::toUpperCase);

        StepVerifier.create(fluxToText)
                .expectNext("TOMAS")
                .expectNext("JOHN")
                .expectNext("LIA")
                .expectNextMatches(x -> x.startsWith("TH"))
                .expectComplete()
                .verify();
    }
}
