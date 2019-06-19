package espacial.piezas;

import espacial.Ataque;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class ArtilleriaTest implements TestDeContrato {

    private Artilleria unaArtilleria;
    private List<Ataque> ataquesRealizados = new LinkedList<>();

    @Test
    void crearArsenalConLaCantidadDeTorpedosDeFotones() {

        final int CANTIDAD = 40;
        unaArtilleria = new Artilleria(CANTIDAD);

        comprobarQue(unArsenalTieneTorpedosDeFotones(CANTIDAD));
    }

    private Postcondicion unArsenalTieneTorpedosDeFotones(int cantidad) {

        return post(() ->
                assertThat(unaArtilleria.contarTorpedosDeFotones())
                        .as("cantidad de torpedos de fotones")
                        .isEqualTo(cantidad));
    }

    @Test
    void lanzarAtaque() {

        final int CANTIDAD_DE_TORPEDOS = 5;
        final int CANTIDAD_DE_LASER = 35;

        dadoQue(fueCreadoUnArsenalConTorpedosDeFotones(CANTIDAD_DE_TORPEDOS));

        IntStream.range(0, CANTIDAD_DE_TORPEDOS + CANTIDAD_DE_LASER)
                .forEach(n -> ataquesRealizados.add(unaArtilleria.lanzarAtaque()));

        comprobarQue(losAtaquesSon(CANTIDAD_DE_TORPEDOS, CANTIDAD_DE_LASER));
    }

    private Precondicion fueCreadoUnArsenalConTorpedosDeFotones(int cantidad) {

        return pre(() -> unaArtilleria = new Artilleria(cantidad));
    }

    private Postcondicion losAtaquesSon(int cantidadDeTorpedos, int cantidadDeLaser) {

        return post(() -> {

            assertThat(ataquesRealizados)
                    .as("ataques lanzados")
                    .hasSize(cantidadDeTorpedos + cantidadDeLaser);

            assertThat(ataquesRealizados.subList(0, cantidadDeTorpedos))
                    .as("ataques con torpedos de fotones")
                    .hasOnlyElementsOfType(AtaqueConTorpedoDeFotones.class);

            assertThat(ataquesRealizados.subList(cantidadDeTorpedos, cantidadDeLaser))
                    .as("ataques con laser")
                    .hasOnlyElementsOfType(AtaqueConLaser.class);
        });
    }
}