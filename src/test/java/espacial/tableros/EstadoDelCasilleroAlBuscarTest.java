package espacial.tableros;

import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstadoDelCasilleroAlBuscarTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(alBuscarDevuelve(0));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupado() {

        final int antimateriaEnLaPieza = 50;
        dadoQue(cuandoSobreLaPiezaSeBuscaDevuelve(SustanciaEspacial.ANTIMATERIA, antimateriaEnLaPieza));

        estado = new Ocupado(CASILLERO, PIEZA);

        comprobarQue(alBuscarDevuelve(antimateriaEnLaPieza));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupadoPorUnaBase() {

        final int antimateriaEnLaBase = 234;
        dadoQue(cuandoSobreLaPiezaSeBuscaDevuelve(SustanciaEspacial.ANTIMATERIA, antimateriaEnLaBase));

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);

        comprobarQue(alBuscarDevuelve(antimateriaEnLaBase));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {

        final int antimateriaEnLaBase = 332;
        dadoQue(cuandoSobreLaPiezaSeBuscaDevuelve(SustanciaEspacial.ANTIMATERIA, antimateriaEnLaBase));

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        comprobarQue(alBuscarDevuelve(antimateriaEnLaBase));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion cuandoSobreLaPiezaSeBuscaDevuelve(SustanciaEspacial unaSustancia, int cantidad) {

        return precondicion(() -> when(PIEZA.buscar(unaSustancia)).thenReturn(cantidad));
    }

    private Postcondicion alBuscarDevuelve(int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(estado.alBuscar(SustanciaEspacial.ANTIMATERIA))
                        .isEqualTo(cantidadEsperada)
        );
    }

}
