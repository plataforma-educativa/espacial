package espacial.piezas.rasgos;

import espacial.piezas.Dureza;

public interface AsteroideAtacable extends PiezaAtacable {

    Dureza obtenerDureza();

    @Override
    default int obtenerPuntosDeTorpedoDeFotones() {

        int puntos = PiezaAtacable.super.obtenerPuntosDeTorpedoDeFotones();

        Dureza dureza = obtenerDureza();

        return dureza.ponderarAtaqueDe(puntos);
    }

    @Override
    default int obtenerPuntosDeLaser() {

        int puntos = PiezaAtacable.super.obtenerPuntosDeLaser();

        Dureza dureza = obtenerDureza();

        return dureza.ponderarAtaqueDe(puntos);
    }
}
