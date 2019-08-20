import espacial.partidas.PartidaEspacialConBaseCentral;
import espacial.tableros.TableroBatallaEspacial;

/**
 * Juego de tablero para programadores donde existen Naves que parten de una Base,
 * pueden trasladar Sustancias que encuentren en el espacio y tienen poder de ataque.
 * En el espacio también existen Asteroides y Agujeros Negros que obstruyen
 * los movimientos de las Naves.
 *
 * @author Mariano Tugnarelli
 * @prioridad 1
 */
public class BatallaEspacial extends PartidaEspacialConBaseCentral {

    /**
     * @post Partida de la Batalla Espacial lista para ser jugada.
     */
    public BatallaEspacial() {

        super(new TableroBatallaEspacial());
    }

    /**
     * @return nombre de la Partida Espacial.
     * @post devuelve el nombre que identifica el juego.
     */
    @Override
    public String obtenerNombre() {

        return "Batalla Espacial";
    }
}
