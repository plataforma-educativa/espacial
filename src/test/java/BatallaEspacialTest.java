
import static espacial.test.Aserciones.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import espacial.Tablero;
import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class BatallaEspacialTest implements Prueba {

    @Test
    public void crearUnObjetoDeTipoBatallaEspacialDejandolaRegistrada() {

        BatallaEspacial batallaEspacial = new BatallaEspacial();
        
        comprobarQue(quedoRegistrada(batallaEspacial));
    }
    
    private Postcondicion quedoRegistrada(BatallaEspacial objeto) {
        
        return postcondicion("quedó registrada la Batalla Espacial", () -> {

            assertThat(BatallaEspacial.obtener()).isSameAs(objeto);
        });
    }
    
    @Test
    public void obtenerTablero() {
        
        BatallaEspacial batalla = new BatallaEspacial();
        
        Tablero tablero = batalla.obtenerTablero();

        comprobarQue(fueInicializadoEl(tablero));
    }

    private Postcondicion fueInicializadoEl(Tablero tablero) {

        return postcondicion("fue inicializado el tablero", () -> {
          
            assertThat(tablero.contarFilas()).as("filas del Tablero").isEqualTo(21);
            assertThat(tablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
            assertThat(tablero.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);

            assertThat(tablero.contarColumnas()).as("columnas del Tablero").isEqualTo(53);
            assertThat(tablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(26);
            assertThat(tablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-26);
            
            assertThat(tablero)
                .tieneBase()
                    .en(0,0)
                .tieneVacio()
                    .en(0,1).en( 0,-1)
                    .en(1,0).en(-1,-0)
                    .en(1,1).en(-1,-1).en(1, -1).en(-1, 1)
                .tieneContenedor()
                    .en(-2,-2)
                    .en(4, 2)
                    .en(2, -7)
                .tieneAsteroide()
                    .en(1,-3)
                    .entre(7, -1, 7, 0)
                    .entre(8, -3, 8, 3)
                    .en(-6, 0)
                    .en(-9, 0)
                    .en(-2, 4)
                    .en(2, 6)
                    .en(2, -5)
                .tieneAgujeroNegro()
                    .en(3, -6)
                    .en(2, -6)
                    .en(1, -6)
                    .en(1, -7)
                .yTieneVacioEnElResto();
        });
    }

    @Test
    public void obtenerTableroSiempreDevuelveElMismoTablero() {
        
        BatallaEspacial batalla = new BatallaEspacial();
        
        Tablero tablero = batalla.obtenerTablero();
        
        comprobarQue(devuelveElMismo(batalla, tablero));
    }

    private Postcondicion devuelveElMismo(BatallaEspacial batalla, Tablero tablero) {

        return postcondicion("devuelve el mismo tablero", () -> {
         
            assertThat(tablero).isSameAs(batalla.obtenerTablero());
            assertThat(tablero).isSameAs(batalla.obtenerTablero());
        });
    }
}
