package espacial;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import espacial.piezas.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class CasilleroTest implements Prueba {

    private Tablero tablero;
    
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
  
    @BeforeEach
    public void crearTablero() {
        
        tablero = new Tablero();
    }
    
    @Test
    public void escanearCuandoNoTienePieza() {
        
        Casillero casillero = tablero.obtenerCasillero(5, 2);
        
        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.VACIO);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnContenedor() {
        
        Casillero casillero = tablero.obtenerCasillero(-9, 0);
        
        casillero.ocuparCon(CONTENEDOR);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.CONTENEDOR);
    }
    
    @Test
    public void escanearCuandoTieneUnaPiezaQueEsUnAsteroide() {
        
        Casillero casillero = tablero.obtenerCasillero(3, 10);
        
        casillero.ocuparCon(ASTEROIDE);

        assertThat(casillero.escanear()).as("espectro escaneado")
            .isEqualTo(EspectroEspacial.ASTEROIDE);
    }

    @Test
    public void moverPiezaEntreCasilleros() {
        
        Casillero origen = tablero.obtenerCasillero(0, 0);
        origen.ocuparCon(NAVE);
        
        Casillero destino = tablero.obtenerCasillero(0, 1);
        
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
        
        Casillero casillero = tablero.obtenerCasillero(4, 9);
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
    
    @Test
    public void estaOcupado() {
        
        Casillero casillero = tablero.obtenerCasillero(3, 4);
        casillero.ocuparCon(ASTEROIDE);

        comprobarQue(estaOcupado(casillero));
    }

    private Postcondicion estaOcupado(Casillero casillero) {

        return postcondicion("el casillero esta ocupado", () -> {
            
            assertThat(casillero.estaOcupado()).as("estaOcupado").isTrue();
            assertThat(casillero.estaDesocupado()).as("estaDesocupado").isFalse();
        });
    }
    
    @Test
    public void estaDesocupado() {
        
        Casillero casillero = tablero.obtenerCasillero(4, -6);
        
        comprobarQue(estaDesocupado(casillero));
    }

    private Postcondicion estaDesocupado(Casillero casillero) {
        
        return postcondicion("el casillero esta desocupado", () -> {
          
            assertThat(casillero.estaOcupado()).as("estaOcupado").isFalse();
            assertThat(casillero.estaDesocupado()).as("estaDesocupado").isTrue();
        });
    }
    
    @Test
    public void obtenerContiguoAlNorte() {
        
        Casillero casillero = tablero.obtenerCasillero(0, 0);
        
        Casillero contiguo = casillero.obtenerContiguoAl(Direccion.NORTE);
        
        comprobarQue(elCasilleroEsEl(1, 0, contiguo));
    }

    @Test
    public void obtenerContiguoAlSur() {
        
        Casillero casillero = tablero.obtenerCasillero(0, 0);
        
        Casillero contiguo = casillero.obtenerContiguoAl(Direccion.SUR);
        
        comprobarQue(elCasilleroEsEl(-1, 0, contiguo));
    }

    private Postcondicion elCasilleroEsEl(int fila, int columna, Casillero casillero) {

        return postcondicion("el Casillero es el esperado", () -> {
         
            assertThat(casillero).as("casillero en [%d, %d]", fila, columna)
                .isSameAs(tablero.obtenerCasillero(fila, columna));
        });
    }
    
}
