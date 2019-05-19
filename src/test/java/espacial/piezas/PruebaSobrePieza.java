package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.*;
import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Prueba;

public abstract class PruebaSobrePieza<T extends Pieza> implements Prueba {

    protected final NaveEspacial NAVE_ESPACIAL = mock(NaveEspacial.class, "NAVE_ESPACIAL");
    protected final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");


    protected abstract T piezaCreada();
    
    protected abstract EspectroEspacial espectroEsperado();
    
    protected abstract Postcondicion laNaveEspacialFueNotificadaDelChoque();

    @Test
    public void escanear() {
        
        Pieza pieza = piezaCreada();
        
        EspectroEspacial espectro = pieza.escanear();
        
        comprobarQue(elEspectroEscaneadoEsElEsperado(espectro));
    }

    private Postcondicion elEspectroEscaneadoEsElEsperado(EspectroEspacial espectro) {
        
        return postcondicion("el EspectroEspacial escaneado", () -> {
           
            assertThat(espectro).isEqualTo(espectroEsperado());
        });
    }
    
    @Test
    public void fueChocadaPor() {
        
        Pieza pieza = piezaCreada();

        pieza.fueChocadaPor(NAVE_ESPACIAL);
        
        comprobarQue(laNaveEspacialFueNotificadaDelChoque());
    }
    
    protected Casillero mockCasillero() {
        
        Casillero casillero = mock(Casillero.class);
        when(casillero.obtenerContiguoEn(Direccion.NORTE)).thenReturn(mock(Casillero.class, "CASILLERO_NORTE"));
        when(casillero.obtenerContiguoEn(Direccion.SUR)).thenReturn(mock(Casillero.class, "CASILLERO_SUR"));
        when(casillero.obtenerContiguoEn(Direccion.OESTE)).thenReturn(mock(Casillero.class, "CASILLERO_OESTE"));
        when(casillero.obtenerContiguoEn(Direccion.ESTE)).thenReturn(mock(Casillero.class, "CASILLERO_ESTE"));
        
        return casillero;
    }

    @Test
    public void obtenerPuntosParaCualquierPieza() {

        Pieza unaPieza = piezaCreada();

        comprobarQue(sePuedenObtenerLosPuntosDe(unaPieza));

    }

    private Postcondicion sePuedenObtenerLosPuntosDe(Pieza unaPieza) {

        return postcondicion("se pueden obtener los puntos de la Pieza", () -> {

            assertThat(unaPieza.obtenerPuntos()).as("puntos")
                    .isBetween(Pieza.PUNTOS_MINIMOS, Pieza.PUNTOS_MAXIMOS);

        });
    }

    @Test
    public void fueAtacadoCon() {

        Pieza unaPieza = piezaCreada();

        unaPieza.fueAtacadoCon(UN_ATAQUE);
    }

}
