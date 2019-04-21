package espacial;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TableroTest {

    @Test
    public void obtenerDimensiones() {
        
        Tablero tablero = new Tablero();
        
        assertThat(tablero.contarFilas()).as("filas").isEqualTo(21);
        assertThat(tablero.contarColumnas()).as("columnas").isEqualTo(53);
        assertThat(tablero.obtenerFilaMinima()).as("fila mínima").isEqualTo(-10);
        assertThat(tablero.obtenerFilaMaxima()).as("fila máxima").isEqualTo(10);
        assertThat(tablero.obtenerColumnaMinima()).as("columna mínima").isEqualTo(-26);
        assertThat(tablero.obtenerColumnaMaxima()).as("columna máxima").isEqualTo(26);
    }
    
    @Test
    public void paraCadaCoordenada() {
        
        Tablero tablero = new Tablero();
        final Set<Coordenada> coordenadasIteradas = new HashSet<>();
        
        tablero.conCadaCoordenada( (fila, columna) -> {
           
            coordenadasIteradas.add(Coordenada.en(fila, columna));
        });
        
        assertThat(coordenadasIteradas).as("conjunto con las coordenadas iteradas")
            .hasSize(tablero.contarFilas() * tablero.contarColumnas());
    }
    
    @Test
    public void obtenerCasillero() {
        
        Tablero tablero = new Tablero();
        
        Casillero casillero = tablero.obtenerCasillero(0,0);
        
        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.BASE);
    }
    
    @Test
    public void obtenerCasilleroConCoordenada() {
        
        Tablero tablero = new Tablero();
        
        Casillero casillero = tablero.obtenerCasillero(Coordenada.en(0, 0));
        
        assertThat(casillero).isEqualTo(tablero.obtenerCasillero(0,0));
        assertThat(casillero.escanear()).isEqualTo(EspectroEspacial.BASE);
    }
}
