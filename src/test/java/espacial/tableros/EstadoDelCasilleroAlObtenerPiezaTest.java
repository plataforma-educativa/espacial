package espacial.tableros;

import espacial.Pieza;
import espacial.test.Postcondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EstadoDelCasilleroAlObtenerPiezaTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(generaUnDefecto(() -> estado.alObtenerPieza()));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);
        
        comprobarQue(alObtenerPiezaDevuelve(PIEZA));        
    }
    
    @Test
    void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        comprobarQue(alObtenerPiezaDevuelve(PIEZA));
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        
        comprobarQue(alObtenerPiezaDevuelve(NAVE));
    }
    
    private Postcondicion alObtenerPiezaDevuelve(Pieza pieza) {

        return post(() -> assertThat(estado.alObtenerPieza()).isSameAs(pieza));
    }
}
