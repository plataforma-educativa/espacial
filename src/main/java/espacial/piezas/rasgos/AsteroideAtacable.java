package espacial.piezas.rasgos;

public interface AsteroideAtacable extends PiezaAtacable {

    int obtenerDureza();

    @Override
    default void atacadoConLaser() {

        decrementarPuntosEn(100 * 5 / obtenerDureza());
    }

    @Override
    default void atacadoConTorpedoDeFotones() {

        decrementarPuntosEn(100 * 10 / obtenerDureza());
    }
}
