package espacial.tableros;

import espacial.Visitante;
import espacial.test.Postcondicion;
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
        comprobarQue(laPiezaFueColocadaEnCasillero());
    }

    private Postcondicion laPiezaFueColocadaEnCasillero() {

        return post(() -> verify(PIEZA).fueColocadaEn(CASILLERO));
    }

    private Precondicion laPiezaEsUnaNave() {

        return pre(() ->
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
        comprobarQue(laPiezaFueColocadaEnCasillero());
    }

    private Precondicion laPiezaEsUnaBase() {

        return pre(() ->
                doAnswer(invocation -> {

                    invocation.getArgument(0, Visitante.class).siEsBase(PIEZA);
                    return null;

                }).when(PIEZA).aceptar(any(Visitante.class))
        );
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
        comprobarQue(laNaveFueColocadaEnCasillero());
    }

    private Postcondicion laNaveFueColocadaEnCasillero() {

        return post(() -> verify(NAVE).fueColocadaEn(CASILLERO));
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alOcuparCon(OTRA_PIEZA);

        comprobarQue(generaUnChoqueEntre(OTRA_PIEZA, NAVE));
    }
}
