package espacial.piezas.rasgos;

import espacial.Atacable;
import espacial.Pieza;

public interface PiezaAtacable extends Pieza, Atacable {

    @Override
    default void atacadoConTorpedoDeFotones() {

        decrementarPuntosEn(10);
    }

    @Override
    default void atacadoConLaser() {

        decrementarPuntosEn(5);
    }

    void decrementarPuntosEn(int decremento);
}
