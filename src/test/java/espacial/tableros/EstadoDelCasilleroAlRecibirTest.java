package espacial.tableros;

import espacial.Carga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlRecibirTest extends EstadoDelCasilleroTest {

    private final Carga UNA_CARGA = mock(Carga.class, "UNA_CARGA");

    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);

        comprobarQue(generaExcepcionPorqueNoPuedeRecibirUnaCarga(() ->  estado.alRecibir(UNA_CARGA)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion generaExcepcionPorqueNoPuedeRecibirUnaCarga(Operacion operacion) {

        return post(condicion ->
                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeRecibirUnaCarga.class)
        );
    }

    @Test
    void siEstaOcupado() {

        estado = new Ocupado(CASILLERO, PIEZA);

        estado.alRecibir(UNA_CARGA);

        comprobarQue(laPiezaRecibeUnaCarga());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion laPiezaRecibeUnaCarga() {

        return post(condicion ->  verify(PIEZA).recibir(UNA_CARGA));
    }

    @Test
    void siEstaOcupadoPorUnaBase() {

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alRecibir(UNA_CARGA);

        comprobarQue(laPiezaRecibeUnaCarga());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alRecibir(UNA_CARGA);

        comprobarQue(laPiezaRecibeUnaCarga());
        comprobarQue(noCambioElEstadoDelCasillero());
    }
}
