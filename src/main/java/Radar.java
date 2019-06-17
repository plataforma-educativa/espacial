import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;

/**
 * El Radar es la herramienta que provee una Nave para conocer el contexto
 * inmediato de la misma.
 *
 * @author Mariano Tugnarelli
 *
 */
public class Radar {

    private NaveEspacial pieza;

    protected Radar(NaveEspacial nave) {

        pieza = nave;
    }

    public Espectro escanearNorte() {

        return escanarEn(Direccion.NORTE);
    }

    public Espectro escanearSur() {

        return escanarEn(Direccion.SUR);
    }

    public Espectro escanearEste() {

        return escanarEn(Direccion.ESTE);
    }

    public Espectro escanearOeste() {

        return escanarEn(Direccion.OESTE);
    }

    private Espectro escanarEn(Direccion direccionElegida) {

        return interpretar(pieza.escanearEn(direccionElegida));
    }

    private Espectro interpretar(EspectroEspacial espectroEspacial) {

        return Traductor.DE_ESPECTROS.interpretar(espectroEspacial);
    }

    private SustanciaEspacial interpretar(Sustancia sustancia) {

        return Traductor.DE_SUSTANCIAS.interpretar(sustancia);
    }

    public int buscarAlNorte(Sustancia sustancia) {

        return buscarEn(Direccion.NORTE, sustancia);
    }

    public int buscarAlSur(Sustancia sustancia) {

        return buscarEn(Direccion.SUR, sustancia);
    }

    public int buscarAlEste(Sustancia sustancia) {

        return buscarEn(Direccion.ESTE, sustancia);
    }

    public int buscarAlOeste(Sustancia sustancia) {

        return buscarEn(Direccion.OESTE, sustancia);
    }

    private int buscarEn(Direccion direccionElegida, Sustancia sustanciaBuscada) {

        return pieza.buscarEn(direccionElegida, interpretar(sustanciaBuscada));
    }

    @Override
    public String toString() {

        /* Devuelve un mensaje descriptivo para que se use al evaluar una variable en el intérprete */
        return "Radar de la Nave";
    }
}
