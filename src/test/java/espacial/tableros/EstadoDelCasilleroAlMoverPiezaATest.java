package espacial.tableros;

import espacial.test.Postcondicion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlMoverPiezaATest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(generaUnDefecto(() -> estado.alMoverPiezaA(CASILLERO_DESTINO)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);

        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }
    
    @Test
    void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }

    private Postcondicion elCasilleroDestinoRecibeLaPiezaDesdeElContexto() {

        return post(condicion -> verify(CASILLERO_DESTINO).recibirPiezaDesde(CASILLERO));
    }
}
