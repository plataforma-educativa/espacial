import espacial.Direccion;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;

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

    public void cargarDesdeNorte(Sustancia sustancia, int cantidad) {

        SustanciaEspacial sustanciaEspacial = Traductor.DE_SUSTANCIAS.interpretar(sustancia);

        pieza.cargarDesde(Direccion.NORTE, sustanciaEspacial.por(cantidad));
    }

    public void cargarDesdeEste(Sustancia sustancia, int cantidad) {

        SustanciaEspacial sustanciaEspacial = Traductor.DE_SUSTANCIAS.interpretar(sustancia);

        pieza.cargarDesde(Direccion.ESTE, sustanciaEspacial.por(cantidad));
    }

    public Radar obtenerRadar() {

        return radar;
    }

    public Monitor obtenerMonitor() {

        return monitor;
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Nave a la espera de comandos";
    }


}
