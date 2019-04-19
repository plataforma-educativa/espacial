
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
            assertThat(tablero.contarColumnas()).as("columnas del Tablero").isEqualTo(53);
            
            assertThat(tablero)
                .tieneBase()
                    .en(0,0)
                .tieneVacio()
                    .en(0,1).en( 0,-1)
                    .en(1,0).en(-1,-0)
                    .en(1,1).en(-1,-1).en(1, -1).en(-1, 1)
                .tieneContenedor()
                    .en(-2,-2)
                    .en(4, 2);
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
