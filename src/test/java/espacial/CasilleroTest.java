package espacial;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.piezas.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class CasilleroTest implements Prueba {

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
    
    private final Pieza NAVE = new Pieza() {

        @Override
        public EspectroEspacial escanear() {
            return EspectroEspacial.NAVE;
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
        
        casillero.ocuparCon(CONTENEDOR);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.CONTENEDOR);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnAsteroide() {
        
        Casillero casillero = new Casillero(3, 10);
        
        casillero.ocuparCon(ASTEROIDE);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.ASTEROIDE);
    }

    @Test
    public void moverPiezaEntreCasilleros() {
        
        Casillero origen = new Casillero(0, 0);
        origen.ocuparCon(NAVE);
        
        Casillero destino = new Casillero(0, 1);
        
        origen.moverPiezaA(destino);
        
        comprobarQue(laPiezaFueMovidaEntreCasilleros(origen, destino));
    }

    private Postcondicion laPiezaFueMovidaEntreCasilleros(Casillero origen, Casillero destino) {

        return postcondicion("la Nave fue movida entre los casilleros", () -> {
            
            assertThat(origen.escanear()).as("espectro del Casillero origen")
                .isEqualTo(EspectroEspacial.VACIO);
            assertThat(destino.escanear()).as("espectro del Casillero destino")
                .isEqualTo(EspectroEspacial.NAVE);
        });
    }
    
    @Test
    public void desocupar() {
        
        Casillero casillero = new Casillero(4, 9);
        casillero.ocuparCon(ASTEROIDE);
        
        casillero.desocupar();

        comprobarQue(quedoDesocupado(casillero));
    }

    private Postcondicion quedoDesocupado(Casillero casillero) {

        return postcondicion("el Casillero quedó desocupado", () -> {
            
            assertThat(casillero.escanear()).as("espectro del casillero")
                .isEqualTo(EspectroEspacial.VACIO);
        });
    }
}
