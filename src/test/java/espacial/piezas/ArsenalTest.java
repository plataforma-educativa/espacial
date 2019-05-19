package espacial.piezas;

import static org.assertj.core.api.Assertions.*;

import espacial.Ataque;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ArsenalTest implements Prueba {

    private Arsenal unArsenal;
    private List<Ataque> ataquesRealizados = new LinkedList<>();

    @Test
    public void lanzarAtaque() {

        final int CANTIDAD_DE_TORPEDOS = 5;
        final int CANTIDAD_DE_LASER = 35;

        dadoQue(fueCreadoUnArsenalConTorpedosDeFotones(CANTIDAD_DE_TORPEDOS));

        IntStream.range(0, CANTIDAD_DE_TORPEDOS + CANTIDAD_DE_LASER)
                 .forEach(n -> ataquesRealizados.add(unArsenal.lanzarAtaque()));

        comprobarQue(losAtaquesSon(CANTIDAD_DE_TORPEDOS, CANTIDAD_DE_LASER));
    }

    private Precondicion fueCreadoUnArsenalConTorpedosDeFotones(int cantidad) {

        return precondicion("fue creado unArsenal con " + cantidad + " torpedos de fotones", () -> {

            unArsenal = new Arsenal(cantidad);
        });
    }

    private Postcondicion losAtaquesSon(int cantidadDeTorpedos, int cantidadDeLaser) {

        return postcondicion("los ataque son " +
                            cantidadDeTorpedos + " de torpedos de fotones y " +
                            cantidadDeLaser + " de lase", () -> {

            assertThat(ataquesRealizados).as("ataques lanzados")
                    .hasSize(cantidadDeTorpedos + cantidadDeLaser);
            assertThat(ataquesRealizados.subList(0, cantidadDeTorpedos)).as("ataques con torpedos de fotones")
                    .hasOnlyElementsOfType(AtaqueConTorpedoDeFotones.class);
            assertThat(ataquesRealizados.subList(cantidadDeTorpedos, cantidadDeLaser)).as("ataques con laser")
                    .hasOnlyElementsOfType(AtaqueConLaser.class);
        });
    }
}