package espacial.tableros;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlEscanearTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.VACIO));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupado() {
        
        dadoQue(cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial.ASTEROIDE));

        estado = new Ocupado(CASILLERO, PIEZA);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.ASTEROIDE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    void siEstaOcupadoPorUnaBase() {
     
        dadoQue(cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial.BASE));

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.BASE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        dadoQue(cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial.BASE));

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.BASE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    private Precondicion cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial espectroEsperado) {
        
        return pre(condicion -> when(PIEZA.escanear()).thenReturn(espectroEsperado));
    }
    
    private Postcondicion alEscanearDevuelve(EspectroEspacial espectro) {
        
        return post(condicion -> assertThat(estado.alEscanear()).isEqualTo(espectro));
    }
}
