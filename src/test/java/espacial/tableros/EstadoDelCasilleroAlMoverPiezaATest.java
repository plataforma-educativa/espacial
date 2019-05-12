package espacial.tableros;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;

public class EstadoDelCasilleroAlMoverPiezaATest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        estado = new Vacio(CASILLERO);
        
        comprobarQue(generaUnDefecto(() -> estado.alMoverPiezaA(CASILLERO_DESTINO)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void siEstaOcupado() {
        
        estado = new Ocupado(CASILLERO, PIEZA);

        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }
    
    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        
        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);

        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }

    private Postcondicion elCasilleroDestinoRecibeLaPiezaDesdeElContexto() {

        return postcondicion("el CASILLERO_DESTINO recibe la Pieza desde el contexto", () -> {
            
            verify(CASILLERO_DESTINO).recibirPiezaDesde(CASILLERO);
        });
    }
}
