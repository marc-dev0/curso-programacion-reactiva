import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class TestUsuarios {
    private static final Logger log = LoggerFactory.getLogger(TestUsuarios.class);
    public static void main(String[] args) {
        Flux<String> nombres = Flux.just("Juan Perez", "Juan Perez1");

        Flux<Usuario> usuarios = nombres.map(n -> new Usuario(n.split(" ")[0].toUpperCase(), n.split(" ")[1].toUpperCase()))
                .filter(u -> !u.getApellido().equalsIgnoreCase("Perez1"))
                .doOnNext(usuario -> {
                    log.info("entro aqui");;
                    if (usuario == null) {
                        throw new RuntimeException("Los nombres no pueden estar vacíos");
                    }
                    System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
                })
                .map(usuario -> {
                    String nombre = usuario.getNombre().toLowerCase();
                    usuario.setNombre(nombre);
                    return usuario;
                });

        usuarios.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), new Runnable() {
            @Override
            public void run() {
                log.info("Se ha finalizado la ejecución del observable con éxito");
            }
        });
    }
}
