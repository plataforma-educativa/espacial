package espacial.tableros;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.excepciones.ElCasilleroEstaOcupado;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
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

        return post(condicion -> verify(PIEZA).fueColocadaEn(CASILLERO));
    }

    private Precondicion laPiezaEsUnaNave() {

        return pre(condicion ->
                doAnswer(invocation -> {

                    invocation.getArgument(0, Pieza.Visitante.class).siEsNave((NaveEspacial) PIEZA);
                    return null;

                }).when(PIEZA).aceptar(any(Pieza.Visitante.class)));
    }

    @Test
    void siEstaVacioOcupandoseConUnaBase() {

        dadoQue(laPiezaEsUnaBase());

        estado = new Vacio(CASILLERO);

        estado.alOcuparCon(BASE);

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBase());
        comprobarQue(laBaseFueColocadaEnCasillero());
    }

    private Precondicion laPiezaEsUnaBase() {

        return pre(condicion ->
                doAnswer(invocation -> {

                    invocation.getArgument(0, Pieza.Visitante.class).siEsBase((BaseEspacial) BASE);
                    return null;

                }).when(BASE).aceptar(any(Pieza.Visitante.class))
        );
    }

    private Postcondicion laBaseFueColocadaEnCasillero() {

        return post(condicion -> verify(BASE).fueColocadaEn(CASILLERO));
    }

    @Test
    void siEstaOcupado() {

        estado = new Ocupado(CASILLERO, PIEZA);

        comprobarQue(generaUnaExcepcionPorqueElCasilleroEstaOcupado(() -> estado.alOcuparCon(OTRA_PIEZA)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion generaUnaExcepcionPorqueElCasilleroEstaOcupado(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ElCasilleroEstaOcupado.class)
        );
    }

    @Test
    void siEstaOcupadoPorUnaBase() {

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alOcuparCon(NAVE);

        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras());
        comprobarQue(laNaveFueColocadaEnCasillero());
    }

    private Postcondicion laNaveFueColocadaEnCasillero() {

        return post(condicion -> verify(NAVE).fueColocadaEn(CASILLERO));
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alOcuparCon(OTRA_PIEZA);

        comprobarQue(generaUnChoqueEntre(OTRA_PIEZA, NAVE));
    }
}
