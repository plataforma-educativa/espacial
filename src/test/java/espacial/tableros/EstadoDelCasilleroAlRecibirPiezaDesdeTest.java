package espacial.tableros;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlRecibirPiezaDesdeTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        dadoQue(elCasilleroDeOrigenTieneUnaPieza());
        
        estado = new Vacio(CASILLERO);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(laPiezaFueMovidaDelOrigenAlCasillero());
    }

    @Test
    void siEstaOcupado() {
        
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado = new Ocupado(CASILLERO, PIEZA);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, PIEZA));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    void siEstaOcupadoPorUnaBase() {
     
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);
        
        comprobarQue(laPiezaFueMovidaDelOrigen());
        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, NAVE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion elCasilleroDeOrigenTieneUnaPieza() {

        return pre(() -> when(CASILLERO_ORIGEN.obtenerPieza()).thenReturn(PIEZA_EN_ORIGEN));
    }
    
    private Postcondicion laPiezaFueMovidaDelOrigenAlCasillero() {
        
        return post(() -> {
            
            verify(CASILLERO_ORIGEN).desocupar();
            verify(CASILLERO).ocuparCon(PIEZA_EN_ORIGEN);
        });
    }
    
    private Postcondicion laPiezaFueMovidaDelOrigen() {
        
        return post(() -> verify(CASILLERO_ORIGEN).desocupar());
    }
    
}
