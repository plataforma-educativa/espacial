package espacial.piezas.rasgos;

import espacial.Chocable;
import espacial.Obstaculo;

public interface NaveChocable extends Obstaculo, Chocable {

    @Override
    default void chocoContraUnAsteroide() {

        disminuirNivelDeEscudosEn(25);
    }

    @Override
    default void chocoContraUnContenedor() {

        disminuirNivelDeEscudosEn(10);
    }

    @Override
    default void chocoContraElBordeDelTablero() {

        disminuirNivelDeEscudosEn(50);
    }

    @Override
    default void chocoContraUnAgujeroNegro() {

        disminuirNivelDeEscudosEn(75);
    }

    @Override
    default void chocoContraUnaNave() {

        disminuirNivelDeEscudosEn(25);
    }

    @Override
    default void chocoContraUnaBase() {

        disminuirNivelDeEscudosEn(5);
    }
    
    @Override
    default void fueChocadaPor(Chocable chocable) {

        chocable.chocoContraUnaNave();
        disminuirNivelDeEscudosEn(5);
    }

    void disminuirNivelDeEscudosEn(int diferencia);
}
