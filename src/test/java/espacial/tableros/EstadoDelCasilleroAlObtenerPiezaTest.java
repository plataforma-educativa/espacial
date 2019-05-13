package espacial.tableros;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.Pieza;
import espacial.test.Postcondicion;

public class EstadoDelCasilleroAlObtenerPiezaTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(generaUnDefecto(() -> estado.alObtenerPieza()));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        comprobarQue(alObtenerPiezaDevuelve(PIEZA));        
    }
    
    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        comprobarQue(alObtenerPiezaDevuelve(PIEZA));
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        comprobarQue(alObtenerPiezaDevuelve(NAVE));
    }
    
    private Postcondicion alObtenerPiezaDevuelve(Pieza pieza) {

        return postcondicion("al obtenerEstadoResultante Pieza devuelve " + pieza, () -> {
          
            assertThat(estado.alObtenerPieza()).isSameAs(pieza);
        });
    }
}
