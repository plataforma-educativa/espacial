import espacial.Casillero;
import espacial.Tablero;
import espacial.piezas.Pieza;

public class Nave {

    private final BatallaEspacial partida;
    private final Pieza pieza;
    
    public Nave() {

        partida = BatallaEspacial.obtener();
        pieza = partida.agregar(this);
    }

    public void avanzarAlNorte() {
        
        Tablero tablero = partida.obtenerTablero();
        
        Casillero origen = tablero.obtenerCasillero(pieza.obtenerPosicion().obtenerFila(), 
                                                    pieza.obtenerPosicion().obtenerColumna());
        Casillero destino = tablero.obtenerCasillero(pieza.obtenerPosicion().obtenerFila() + 1,
                                                     pieza.obtenerPosicion().obtenerColumna());
        origen.moverPiezaA(destino);
    }
}
