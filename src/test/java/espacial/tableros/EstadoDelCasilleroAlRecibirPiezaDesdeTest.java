package espacial.tableros;

import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EstadoDelCasilleroAlRecibirPiezaDesdeTest extends EstadoDelCasilleroTest {
    
    @Test
    void siEstaVacio() {

        dadoQue(elCasilleroDeOrigenTieneUnaNave());
        
        estado = new Vacio(CASILLERO);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(laPiezaFueMovidaDelOrigenAlCasillero());
        comprobarQue(cambioElEstadoDelCasilleroPorOcupado());
        comprobarQue(noSeNotificaAlCasillero());
    }

    private Postcondicion noSeNotificaAlCasillero() {

        return post(condicion ->  verify(CASILLERO, never()).fueAgregadaAlTablero(any(Pieza.class)));
    }

    @Test
    void siEstaOcupado() {

        dadoQue(elCasilleroDeOrigenTieneUnaNave());

        estado = new Ocupado(CASILLERO, PIEZA);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, PIEZA));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    void siEstaOcupadoPorUnaBase() {
     
        dadoQue(elCasilleroDeOrigenTieneUnaNave());

        estado = new OcupadoPorUnaBase(CASILLERO, PIEZA);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, PIEZA));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    void siEstaOcupadoPorUnaBaseConNaveEnManiobras() {
     
        dadoQue(elCasilleroDeOrigenTieneUnaNave());

        estado = new OcupadoPorUnaBaseConNaveEnManiobras(CASILLERO, PIEZA, NAVE);
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(generaUnChoqueEntre(PIEZA_EN_ORIGEN, NAVE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion elCasilleroDeOrigenTieneUnaNave() {

        return pre(condicion ->  {

            when(CASILLERO_ORIGEN.obtenerPieza()).thenReturn(PIEZA_EN_ORIGEN);

            doAnswer(invocacion -> {

                invocacion.getArgument(0, Pieza.Visitante.class).siEsNave((NaveEspacial) PIEZA_EN_ORIGEN);
                return null;

            }).when(PIEZA_EN_ORIGEN).aceptar(any(Pieza.Visitante.class));
        });
    }
    
    private Postcondicion laPiezaFueMovidaDelOrigenAlCasillero() {
        
        return post(condicion ->  {
            
            verify(CASILLERO_ORIGEN).desocupar();
        });
    }
    
    private Postcondicion laPiezaFueMovidaDelOrigen() {
        
        return post(condicion ->  verify(CASILLERO_ORIGEN).desocupar());
    }
    
}
