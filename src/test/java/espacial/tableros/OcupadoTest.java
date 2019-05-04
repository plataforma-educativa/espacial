package espacial.tableros;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;

public class OcupadoTest extends EstadoDelCasilleroTest<Ocupado> {
    
    private final Pieza PIEZA =  mock(Pieza.class);
    
    @BeforeEach
    public void inicializar() {

        estado = new Ocupado(CASILLERO, PIEZA);
    }

    @Test
    public void alEscanear() {

        dadoQue(cuandoLaPiezaEsEscaneadaDevuelveAsteroide());
        
        comprobarQue(alEscanearDevuelve(EspectroEspacial.ASTEROIDE));
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion cuandoLaPiezaEsEscaneadaDevuelveAsteroide() {

        return precondicion("cuando la Pieza es escaneada devuelve ASTEROIDE", () -> {
          
            when(PIEZA.escanear()).thenReturn(EspectroEspacial.ASTEROIDE);
        });
    }
    
    @Test
    public void alDesocupar() {
        
        estado.alDesocupar();
        
        comprobarQue(cambioElEstadoDelCasilleroPorVacio());
    }
    
    @Test
    public void alMoverPiezaA() {

        dadoQue(elCasilleroDestinoEstaVacio());
        
        estado.alMoverPiezaA(CASILLERO_DESTINO);
        
        comprobarQue(elCasilleroDestinoRecibeLaPiezaDesdeElContexto());
    }
    
    private Precondicion elCasilleroDestinoEstaVacio() {

        return precondicion("el Casillero destino esta VACIO", () -> {
            
            when(CASILLERO_DESTINO.obtenerPieza()).thenReturn(null);
        });
    }

    private Postcondicion elCasilleroDestinoRecibeLaPiezaDesdeElContexto() {

        return postcondicion("el Casillero destino recibe la Pieza desde el contexto", () -> {
            
            verify(CASILLERO_DESTINO).recibirPiezaDesde(CASILLERO);
        });
    }
    
    @Test
    public void alObtenerPieza() {

        comprobarQue(alObtenerPiezaDevuelve(PIEZA));
    }
    
    private Postcondicion alObtenerPiezaDevuelve(Pieza pieza) {

        return postcondicion("al obtener Pieza la devuelve", () -> {
          
            assertThat(estado.alObtenerPieza()).isSameAs(pieza);
        });
    }

    @Test
    public void alOcuparCon() {

        comprobarQue(generaUnDefecto(() -> estado.alOcuparCon(OTRA_PIEZA)));
        comprobarQue(noCambioElEstadoDelCasillero());
    }
    
    @Test
    public void alRecibirPiezaDesde() {
        
        dadoQue(elCasilleroDeOrigenTieneUnaPieza());

        estado.alRecibirPiezaDesde(CASILLERO_ORIGEN);

        comprobarQue(laPiezaDelCasilleroOrigenChocaConLaPiezaDelCasillero());
        comprobarQue(noCambioElEstadoDelCasillero());
    }

    private Precondicion elCasilleroDeOrigenTieneUnaPieza() {

        return precondicion("el Casillero de origen tiene una Pieza", () -> {

            when(CASILLERO_ORIGEN.obtenerPieza()).thenReturn(OTRA_PIEZA);
        });
    }

    private Postcondicion laPiezaDelCasilleroOrigenChocaConLaPiezaDelCasillero() {

        return postcondicion("la Pieza del Casillero origen choca con la Pieza del Casillero", () -> {
          
            verify(OTRA_PIEZA).chocarCon(PIEZA);
        });
    }
}
