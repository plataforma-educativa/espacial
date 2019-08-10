package espacial.piezas.rasgos;

import espacial.Atacable;
import espacial.Ataque;
import espacial.Pieza;

public interface PiezaAtacable extends Pieza, Atacable {

    @Override
    default void fueAtacadoCon(Ataque ataque) {

        ataque.aplicarSobre(this);

        despuesDeSerAtacadoCon(ataque);
    }

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

    default void despuesDeSerAtacadoCon(Ataque unAtaque) {

    }

    void decrementarPuntosEn(int decremento);

}
