import espacial.NaveEspacial;

public class Monitor {

    private NaveEspacial pieza;

    protected Monitor(NaveEspacial nave) {

        pieza = nave;
    }

    public int consultarNivelDeEscudos() {

        return pieza.obtenerNivelDeEscudos();
    }

    public int consultarCantidadDeTorpedos() {

        return pieza.obtenerCantidadDeTorpedosDeFotones();
    }
}
