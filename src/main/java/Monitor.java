import espacial.NaveEspacial;

public class Monitor {

    private final NaveEspacial pieza;

    protected Monitor(NaveEspacial nave) {

        pieza = nave;
    }

    public int consultarNivelDeEscudos() {

        return pieza.obtenerNivelDeEscudos();
    }

    public int consultarCantidadDeTorpedos() {

        return pieza.obtenerCantidadDeTorpedosDeFotones();
    }

    public int consultarNivelDeCarga() {

        return pieza.obtenerNivelDeCarga();
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Monitor de la Nave";
    }
}
