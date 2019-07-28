package espacial.piezas;

import espacial.Accion;
import espacial.Ataque;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtilleriaTest implements TestDeContrato {

    private final Accion UNA_ACCION = mock(Accion.class, "UNA_ACCION");
    private Artilleria unaArtilleria;
    private List<Ataque> ataquesRealizados = new LinkedList<>();

    @Test
    void crearArsenalConLaCantidadDeTorpedosDeFotones() {

        final int CANTIDAD = 40;
        unaArtilleria = new Artilleria(CANTIDAD);

        comprobarQue(unArsenalTieneTorpedosDeFotones(CANTIDAD));
    }

    private Postcondicion unArsenalTieneTorpedosDeFotones(int cantidad) {

        return post(condicion ->
                assertThat(unaArtilleria.contarTorpedosDeFotones())
                        .as("cantidad de torpedos de fotones")
                        .isEqualTo(cantidad));
    }

    @Test
    void lanzarAtaque() {

        final int CANTIDAD_DE_TORPEDOS = 5;
        final int CANTIDAD_DE_LASER = 35;

        dadoQue(fueCreadoUnArsenalConTorpedosDeFotones(CANTIDAD_DE_TORPEDOS));

        repetir(CANTIDAD_DE_TORPEDOS + CANTIDAD_DE_LASER,
                i -> ataquesRealizados.add(unaArtilleria.lanzarAtaque()));

        comprobarQue(losAtaquesSon(CANTIDAD_DE_TORPEDOS, CANTIDAD_DE_LASER));
    }

    private Precondicion fueCreadoUnArsenalConTorpedosDeFotones(int cantidad) {

        return pre(condicion ->  unaArtilleria = new Artilleria(cantidad));
    }

    private Postcondicion losAtaquesSon(int cantidadDeTorpedos, int cantidadDeLaser) {

        return post(condicion ->  {

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

    @Test
    void cuandoCambianLasMuniciones() {

        dadoQue(fueCreadoUnArsenalConTorpedosDeFotones(5));

        unaArtilleria.cuandoCambianLasMuniciones(UNA_ACCION);
        unaArtilleria.lanzarAtaque();

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    private Postcondicion ejecutoUnaAccionConfigurada() {

        return post(condicion -> verify(UNA_ACCION).ejecutar());
    }
}