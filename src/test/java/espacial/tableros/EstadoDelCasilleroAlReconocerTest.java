package espacial.tableros;

import espacial.Faccion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlReconocerTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(alReconocerDevuelve(Faccion.NEUTRAL));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupado() {

        dadoQue(cuandoLaPiezaEsReconocidaDevuelve(Faccion.ALIADO));

        estado = new Ocupado(CASILLERO, PIEZA);

        comprobarQue(alReconocerDevuelve(Faccion.ALIADO));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBase() {

        dadoQue(cuandoLaPiezaEsReconocidaDevuelve(Faccion.RIVAL));

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        comprobarQue(alReconocerDevuelve(Faccion.RIVAL));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        dadoQue(cuandoLaPiezaEsReconocidaDevuelve(Faccion.ALIADO));

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        comprobarQue(alReconocerDevuelve(Faccion.ALIADO));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion cuandoLaPiezaEsReconocidaDevuelve(Faccion faccionEsperada) {

        return pre(condicion -> when(PIEZA.reconocer()).thenReturn(faccionEsperada));
    }

    private Postcondicion alReconocerDevuelve(Faccion faccion) {

        return post(condicion -> assertThat(estado.alReconocer()).isEqualTo(faccion));

    }
}
