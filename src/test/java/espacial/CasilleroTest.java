package espacial;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.piezas.Pieza;

public class CasilleroTest {

    private final Pieza CONTENEDOR = new Pieza() {

        @Override
        public EspectroEspacial escanear() {
            return EspectroEspacial.CONTENEDOR;
        }
    };
    
    private final Pieza ASTEROIDE = new Pieza() {

        @Override
        public EspectroEspacial escanear() {
            return EspectroEspacial.ASTEROIDE;
        }
    };
    
    @Test
    public void escanearCuandoNoTienePieza() {
        
        Casillero casillero = new Casillero(5, 2);
        
        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.VACIO);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnContenedor() {
        
        Casillero casillero = new Casillero(-9, 0);
        
        casillero.colocar(CONTENEDOR);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.CONTENEDOR);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnAsteroide() {
        
        Casillero casillero = new Casillero(3, 10);
        
        casillero.colocar(ASTEROIDE);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.ASTEROIDE);
    }
    
}
