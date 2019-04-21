package espacial.piezas;

import espacial.Coordenada;
import espacial.EspectroEspacial;

public interface Pieza {
    
    EspectroEspacial escanear();
    
    default void posicionar(Coordenada coordenada) {
        
    }
    
    default Coordenada obtenerPosicion() {
        
        return null;
    }
}
