package espacial;

public interface Pieza {
    
    EspectroEspacial escanear();
    
    default void fueColocadaEn(Casillero casillero) {
        
    }
    
    default void moverEn(Direccion direccion) {
        
    }
}
