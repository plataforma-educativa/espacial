package espacial;

public interface PiezaMovil extends Pieza {
    
    void despegar();
    
    void moverEn(Direccion direccion);

    int obtenerNivelDeEscudos();
    
    void chocoContraUnAsteroide();

    void chocoContraUnContenedor();

    void chocoContraElBordeDelTablero();
    
    void chocoContraUnAgujeroNegro();

    void chocoContraUnaNave();

    void fueAmarradaCon(Amarre amarre);
}
