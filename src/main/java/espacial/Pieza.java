package espacial;

public interface Pieza {
    
    EspectroEspacial escanear();
    
    default void fueColocadaEn(Casillero casillero) {
        
    }
    
    default void moverEn(Direccion direccion) {
        
    }
    
    default void posicionar(Coordenada coordenada) {
        
    }
    
    default Coordenada obtenerPosicion() {
        
        return null;
    }
}
