package espacial.tableros;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class EstadoDelCasilleroAlRecibirPiezaDesdeTest extends EstadoDelCasilleroTest {
    
    @Test
    public void siEstaVacio() {

        dadoQue(elCasilleroDeOrigenTieneUnaPieza());
        
        estado = new Vacio(CASILLERO);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(laPiezaFueMovidaDelOrigenAlCasillero());
    }

    @Test
    public void siEstaOcupado() {
        
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado = new Ocupado(CASILLERO, PIEZA);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, PIEZA));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void siEstaOcupadoPorUnaBase() {
     
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);
        
        comprobarQue(laPiezaFueMovidaDelOrigen());
        comprobarQue(cambioElEstadoDelCasilleroPorOcupadoPorUnaBaseConNaveEnManiobras());
    }

    @Test
    public void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, NAVE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion elCasilleroDeOrigenTieneUnaPieza() {

        return precondicion(() -> when(CASILLERO_ORIGEN.obtenerPieza()).thenReturn(PIEZA_EN_ORIGEN));
    }
    
    private Postcondicion laPiezaFueMovidaDelOrigenAlCasillero() {
        
        return postcondicion(() -> {
            
            verify(CASILLERO_ORIGEN).desocupar();
            verify(CASILLERO).ocuparCon(PIEZA_EN_ORIGEN);
        });
    }
    
    private Postcondicion laPiezaFueMovidaDelOrigen() {
        
        return postcondicion(() -> verify(CASILLERO_ORIGEN).desocupar());
    }
    
}
