package espacial.tableros;

import espacial.Visitante;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlOcuparConTest extends EstadoDelCasilleroTest {

    @Test
    void siEstaVacio() {

        dadoQue(laPiezaEsUnaNave());

        estado = new Vacio(CASILLERO);

        estado.alOcuparCon(PIEZA);

        comprobarQue(cambioElEstadoDelCasilleroPorOcupado());
    }

    private Precondicion laPiezaEsUnaNave() {

        return precondicion(() ->
                doAnswer(invocation -> {

                    invocation.getArgument(0, Visitante.class).siEsNave(PIEZA);
                    return null;

                }).when(PIEZA).aceptar(any(Visitante.class)));
    }

    @Test
    void siEstaVacioOcupandoseConUnaBase() {

        dadoQue(laPiezaEsUnaBase());

        estado = new Vacio(CASILLERO);

        estado.alOcuparCon(PIEZA);

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBase());
    }

    private Precondicion laPiezaEsUnaBase() {

        return precondicion(() ->
                doAnswer(invocation -> {

                    invocation.getArgument(0, Visitante.class).siEsBase(PIEZA);
                    return null;

                }).when(PIEZA).aceptar(any(Visitante.class)));
    }

    @Test
    void siEstaOcupado() {

        estado = new Ocupado(CASILLERO, PIEZA);

        comprobarQue(generaUnDefecto(() -> estado.alOcuparCon(OTRA_PIEZA)));

        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBase() {

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alOcuparCon(NAVE);

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alOcuparCon(OTRA_PIEZA);

        comprobarQue(generaUnChoqueEntre(OTRA_PIEZA, NAVE));
    }
}
