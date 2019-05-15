package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;

public interface PiezaMovil extends Pieza {
    
    void despegar();
    
    void moverEn(Direccion direccion);

    int obtenerNivelDeEscudos();
    
    void chocoContraUnAsteroide();

    void chocoContraUnContenedor();

    void chocoContraElBordeDelTablero();
    
    void chocoContraUnAgujeroNegro();

    void chocoContraUnaNave();

    void chocoContraUnaBase();

    void fueAmarradaCon(Amarre amarre);

    default void chocarCon(Obstaculo obstaculo) {

        obstaculo.fueChocadaPor(this);
    }
}
