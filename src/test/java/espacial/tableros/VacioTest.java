package espacial.tableros;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class VacioTest extends EstadoDelCasilleroTest<Vacio> {

    private final Pieza PIEZA_EN_ORIGEN = mock(Pieza.class);
    
    @BeforeEach
    public void inicializar() {
        
        estado = new Vacio(CASILLERO);
    }
    
    @Test
    public void alEscanear() {
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.VACIO));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    @Test
    public void alDesocupar() {
        
        comprobarQue(generaUnDefecto(() -> estado.alDesocupar()));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void alMoverPiezaA() {

        comprobarQue(generaUnDefecto(() -> estado.alMoverPiezaA(CASILLERO_DESTINO)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void alObtenerPieza() {

        comprobarQue(generaUnDefecto(() -> estado.alObtenerPieza()));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void alOcuparCon() {
        
        Pieza unaPieza = mock(Pieza.class);
        
        estado.alOcuparCon(unaPieza);
        
        comprobarQue(cambioElEstadoDelCasilleroPorOcupado());
    }
    
    @Test
    public void alRecibirPiezaDesde() {
        
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());
        
        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(laPiezaFueMovidaDelOrigenAlCasillero());
    }

    private Precondicion elCasilleroDeOrigenTieneUnaPieza() {

        return precondicion("el Casillero de origen tiene una Pieza", () -> {
            
            when(CASILLERO_ORIGEN.obtenerPieza()).thenReturn(PIEZA_EN_ORIGEN);
        });
    }

    private Postcondicion laPiezaFueMovidaDelOrigenAlCasillero() {
        
        return postcondicion("la Pieza fue movide del Casillero origen al Casillero contexto", () -> {
            
            verify(CASILLERO_ORIGEN).desocupar();
            verify(CASILLERO).ocuparCon(PIEZA_EN_ORIGEN);
        });
    }
}
