package espacial;

public interface BaseEspacial extends Pieza {

    int obtenerNivelDeDefensas();

    void amarrar(NaveEspacial pieza);

    Amarre[] obtenerAmarres();

    default boolean tieneNavesAmarradas() {

        return obtenerAmarres().length > 0;
    }
}
