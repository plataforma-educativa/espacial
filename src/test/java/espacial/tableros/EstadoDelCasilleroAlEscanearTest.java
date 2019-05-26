package espacial.tableros;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class EstadoDelCasilleroAlEscanearTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.VACIO));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupado() {
        
        dadoQue(cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial.ASTEROIDE));

        estado = new Ocupado(CASILLERO, PIEZA);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.ASTEROIDE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        dadoQue(cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial.BASE));

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.BASE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        dadoQue(cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial.BASE));

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.BASE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    private Precondicion cuandoLaPiezaEsEscaneadaDevuelve(EspectroEspacial espectroEsperado) {
        
        return precondicion(() -> when(PIEZA.escanear()).thenReturn(espectroEsperado));
    }
    
    private Postcondicion alEscanearDevuelve(EspectroEspacial espectro) {
        
        return postcondicion(() -> assertThat(estado.alEscanear()).isEqualTo(espectro));
    }
}
