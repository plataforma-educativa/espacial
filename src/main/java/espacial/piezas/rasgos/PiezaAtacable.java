package espacial.piezas.rasgos;

import espacial.Atacable;

public interface PiezaAtacable extends Atacable {

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
