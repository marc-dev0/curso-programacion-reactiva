import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
public class Ejemplo01 {
    public static void main(String[] args) {
        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromLux = new ArrayList<>();

        //Creación de un mono
        Mono<Integer> mono = Mono.just(121);

        //Creamos un flux
        Flux<Integer> flux = Flux.just(12,14,322,20,24,33,22);

        //Nos sucribimos al mono
        mono.subscribe(elementosFromMono::add);

        //Nos suscribimos al flux
        flux.subscribe(elementosFromLux::add);

        System.out.println(elementosFromMono);
        System.out.println(elementosFromLux);
    }
}
