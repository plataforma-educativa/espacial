package espacial.piezas.rasgos;

import espacial.Atacable;
import espacial.Pieza;

public interface PiezaAtacable extends Pieza, Atacable {

    default int obtenerPuntosDeTorpedoDeFotones() {

        return 10;
    }

    default int obtenerPuntosDeLaser() {

        return 5;
    }

    @Override
    default void atacadoConTorpedoDeFotones() {

        decrementarPuntosEn(obtenerPuntosDeTorpedoDeFotones());
    }

    @Override
    default void atacadoConLaser() {

        decrementarPuntosEn(obtenerPuntosDeLaser());
    }

    void decrementarPuntosEn(int decremento);
}
