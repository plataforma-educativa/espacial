package espacial.tableros;

import espacial.Carga;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlEntregarTest extends EstadoDelCasilleroTest {

    private final Carga UNA_CARGA = mock(Carga.class, "UNA_CARGA");

    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);

        comprobarQue(generaExcepcionPorqueNoPuedeEntregarUnaCarga(() -> estado.alEntregar(UNA_CARGA)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion generaExcepcionPorqueNoPuedeEntregarUnaCarga(Ejecutable ejecutable) {

        return post(() ->
                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeEntregarUnaCarga.class)
        );
    }

    @Test
    void siEstaOcupado() {

        estado = new Ocupado(CASILLERO, PIEZA);

        estado.alEntregar(UNA_CARGA);

        comprobarQue(laPiezaEntregaUnaCarga());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Postcondicion laPiezaEntregaUnaCarga() {

        return post(() -> verify(PIEZA).entregar(UNA_CARGA));
    }

    @Test
    void siEstaOcupadoPorUnaBase() {

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        estado.alEntregar(UNA_CARGA);

        comprobarQue(laPiezaEntregaUnaCarga());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alEntregar(UNA_CARGA);

        comprobarQue(laPiezaEntregaUnaCarga());
        comprobarQue(noCambioElEstadoDelCasillero());
    }
}
