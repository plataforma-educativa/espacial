import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.SustanciaEspacial;

/**
 * El Radar es la herramienta que provee una Nave para conocer el contexto
 * inmediato de la misma.
 *
 * @author Mariano Tugnarelli
 * @prioridad 4
 */
public class Radar {

    private NaveEspacial pieza;

    protected Radar(NaveEspacial nave) {

        pieza = nave;
    }

    /**
     * @return Espectro al NORTE.
     * @post escanea el Casillero contiguo en dirección NORTE y devuelve el Espectro encontrado.
     */
    public Espectro escanearNorte() {

        return escanarEn(Direccion.NORTE);
    }

    /**
     * @return Espectro al SUR.
     * @post escanea el Casillero contiguo en dirección SUR y devuelve el Espectro encontrado.
     */
    public Espectro escanearSur() {

        return escanarEn(Direccion.SUR);
    }

    /**
     * @return Espectro al ESTE.
     * @post escanea el Casillero contiguo en dirección ESTE y devuelve el Espectro encontrado.
     */
    public Espectro escanearEste() {

        return escanarEn(Direccion.ESTE);
    }

    /**
     * @return Espectro al OESTE.
     * @post escanea el Casillero contiguo en dirección OESTE y devuelve el Espectro encontrado.
     */
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

    /**
     * @param sustancia tipo de Sustancia buscada.
     * @return cantidad de Sustancia encontrada.
     * @post busca en el Casillero contiguo en dirección NORTE y devuelve la cantidad de Sustancia encontrada.
     */
    public int buscarAlNorte(Sustancia sustancia) {

        return buscarEn(Direccion.NORTE, sustancia);
    }

    /**
     * @param sustancia tipo de Sustancia buscada.
     * @return cantidad de Sustancia encontrada.
     * @post busca en el Casillero contiguo en dirección SUR y devuelve la cantidad de Sustancia encontrada.
     */
    public int buscarAlSur(Sustancia sustancia) {

        return buscarEn(Direccion.SUR, sustancia);
    }

    /**
     * @param sustancia tipo de Sustancia buscada.
     * @return cantidad de Sustancia encontrada.
     * @post busca en el Casillero contiguo en dirección ESTE y devuelve la cantidad de Sustancia encontrada.
     */
    public int buscarAlEste(Sustancia sustancia) {

        return buscarEn(Direccion.ESTE, sustancia);
    }

    /**
     * @param sustancia tipo de Sustancia buscada.
     * @return cantidad de Sustancia encontrada.
     * @post busca en el Casillero contiguo en dirección OESTE y devuelve la cantidad de Sustancia encontrada.
     */
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
