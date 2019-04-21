import espacial.Casillero;
import espacial.Tablero;
import espacial.piezas.Pieza;

public class Nave {

    private final Pieza pieza;
    
    public Nave() {

        pieza = BatallaEspacial.obtener().agregar(this);
    }

    public void avanzarAlNorte() {
        
        BatallaEspacial batalla = BatallaEspacial.obtener();
        
        Tablero tablero = batalla.obtenerTablero();
        
        Casillero origen = tablero.obtenerCasillero(pieza.obtenerPosicion().obtenerFila(), 
                                                    pieza.obtenerPosicion().obtenerColumna());
        origen.colocar(null);
        
        Casillero destino = tablero.obtenerCasillero(pieza.obtenerPosicion().obtenerFila() + 1,
                                                     pieza.obtenerPosicion().obtenerColumna());
        destino.colocar(pieza);
    }
}
