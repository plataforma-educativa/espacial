import espacial.NaveEspacial;

/**
 * El Monitor se encuentra asociado a una Nave de la cual reporta su condición.
 *
 * @author Mariano Tugnarelli
 * @prioridad 6
 */
public class Monitor {

    private final NaveEspacial pieza;

    protected Monitor(NaveEspacial nave) {

        pieza = nave;
    }

    /**
     * @return porcentaje comprendido entre [0..100]
     * @post nivel de defensa que tiene la Nave. La Nave se destruye cuando llega a 0.
     */
    public int consultarNivelDeEscudos() {

        return pieza.obtenerNivelDeEscudos();
    }

    /**
     * @return cantidad de torpedos.
     * @post devuelve la cantidad de torpedos de fotones que tiene disponible la Nave para hacer ataques.
     */
    public int consultarCantidadDeTorpedos() {

        return pieza.obtenerCantidadDeTorpedosDeFotones();
    }

    /**
     * @return porcentaje comprendido entre [0..100]
     * @post devuelve un valor que representa el porcentaje de la bodega que es ocupado por la carga.
     * El valor 0 indica que no existe carga, bodega vacía.
     * El valor 100 indica que la bodega está completa.
     */
    public int consultarNivelDeCarga() {

        return pieza.obtenerNivelDeCarga();
    }

    /**
     * @param sustancia tipifica la carga.
     * @return cantida de Sustancia.
     * @post devuelve la cantidad de la Sustancia indicada que existe en la bodega de la Nave.
     */
    public int consultarCargaDe(Sustancia sustancia) {

        return pieza.buscar(Traductor.DE_SUSTANCIAS.interpretar(sustancia));
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Monitor de la Nave";
    }
}
