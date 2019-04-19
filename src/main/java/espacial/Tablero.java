package espacial;

import java.util.HashMap;
import java.util.Map;

public class Tablero {

    private Map<Coordenada, EspectroEspacial> casilleros = new HashMap<>();
    
    public Tablero() {
        
        colocarEnCoordenada(0, 0, EspectroEspacial.BASE);
        colocarEnCoordenada(-2, -2, EspectroEspacial.CONTENEDOR);
        colocarEnCoordenada(4, 2, EspectroEspacial.CONTENEDOR);
    }

    private void colocarEnCoordenada(int fila, int columna, EspectroEspacial espectro) {
        
        casilleros.put(conCoordenada(fila, columna), espectro);
    }
    
    public int contarColumnas() {

        return 53;
    }

    public int contarFilas() {

        return 21;
    }

    public EspectroEspacial obtener(int fila, int columna) {

        return casilleros.getOrDefault(conCoordenada(fila, columna), EspectroEspacial.VACIO); 
    }

    private Coordenada conCoordenada(int fila, int columna) {
        
        return new Coordenada(fila, columna);
    }
}
