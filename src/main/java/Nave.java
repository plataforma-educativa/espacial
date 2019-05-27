import espacial.Direccion;
import espacial.NaveEspacial;

public class Nave {

    private final BatallaEspacial partida;
    private final NaveEspacial pieza;
    private final Radar radar;
    private final Monitor monitor;
    
    public Nave() {

        partida = BatallaEspacial.obtener();
        pieza = partida.intervenirCon(this);
        radar = new Radar(pieza);
        monitor = new Monitor(pieza);
    }

    public void despegar() {

        pieza.despegar();
    }

    public void avanzarAlNorte() {
        
        pieza.moverEn(Direccion.NORTE);
    }

    public void avanzarAlSur() {

        pieza.moverEn(Direccion.SUR);
    }

    public void avanzarAlEste() {

        pieza.moverEn(Direccion.ESTE);
    }

    public void avanzarAlOeste() {

        pieza.moverEn(Direccion.OESTE);
    }

    public int consultarNivelDeEscudos() {

        return pieza.obtenerNivelDeEscudos();
    }

    public void atacarAlNorte() {

        pieza.atacarEn(Direccion.NORTE);
    }

    public void atacarAlSur() {

        pieza.atacarEn(Direccion.SUR);
    }

    public void atacarAlEste() {

        pieza.atacarEn(Direccion.ESTE);
    }

    public void atacarAlOeste() {

        pieza.atacarEn(Direccion.OESTE);
    }

    public Radar obtenerRadar() {

        return radar;
    }

    public Monitor obtenerMonitor() {

        return monitor;
    }
}
