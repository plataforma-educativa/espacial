package espacial.piezas;

import espacial.Direccion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import espacial.utiles.Accion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class RumboTest implements TestDeContrato {

    private final Accion UNA_ACCION = mock(Accion.class, "UNA_ACCION");

    private Rumbo unRumbo;

    @Test
    void crearConNorte() {

        dadoQue(fueCreadoUnRumbo());

        comprobarQue(alObtenerEs(Direccion.NORTE));
    }

    private Precondicion fueCreadoUnRumbo() {

        return pre(condicion -> unRumbo = new Rumbo());
    }

    private Postcondicion alObtenerEs(Direccion direccionEsperada) {

        return post(condicion -> assertThat(unRumbo.obtener()).as("obtener()").isEqualTo(direccionEsperada));
    }

    @Test
    void tomar() {

        dadoQue(fueCreadoUnRumbo());

        unRumbo.tomar(Direccion.SUR);

        comprobarQue(alObtenerEs(Direccion.SUR));
    }

    @Test
    void cuandoCambiaNotifica() {

        dadoQue(fueCreadoUnRumbo());

        unRumbo.cuandoCambia(UNA_ACCION);
        unRumbo.tomar(Direccion.OESTE);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    private Postcondicion ejecutoUnaAccionConfigurada() {

        return post(condicion -> verify(UNA_ACCION).ejecutar());
    }

    @Test
    void cuandoNoCambiaNoNotifica() {

        dadoQue(fueCreadoUnRumbo());

        unRumbo.cuandoCambia(UNA_ACCION);
        unRumbo.tomar(Direccion.NORTE);

        comprobarQue(noEjecutoLaAccionConfigurada());

    }

    private Postcondicion noEjecutoLaAccionConfigurada() {

        return post(condicion -> verifyNoMoreInteractions(UNA_ACCION));
    }
}