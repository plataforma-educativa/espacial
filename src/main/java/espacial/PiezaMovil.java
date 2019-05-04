package espacial;

public interface PiezaMovil extends Pieza {
    
    void moverEn(Direccion direccion);

    int obtenerNivelDeEscudos();
    
    void chocoContraUnAsteroide();

    void chocoContraUnContenedor();

    void chocoContraElBordeDelTablero();
    
    void chocoContraUnAgujeroNegro();
}
