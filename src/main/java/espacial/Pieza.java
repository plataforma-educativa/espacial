package espacial;

public interface Pieza {
    
    EspectroEspacial escanear();
    
    default void posicionar(Coordenada coordenada) {
        
    }
    
    default Coordenada obtenerPosicion() {
        
        return null;
    }
}
