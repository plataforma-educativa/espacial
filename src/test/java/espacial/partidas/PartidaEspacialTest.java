package espacial.partidas;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class PartidaEspacialTest {

    @Test
    void crear() throws InterruptedException {

        assertTimeout(Duration.ofSeconds(3), () -> {

            PartidaEspacial partida = new PartidaEspacial();
            partida.iniciar();
        });

        TimeUnit.SECONDS.sleep(60);
    }
}