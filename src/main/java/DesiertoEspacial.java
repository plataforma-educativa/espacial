import espacial.partidas.PartidaEspacialConBaseCentral;
import espacial.tableros.TableroDesierto;

/**
 * Partida Espacial con un Tablero vacío de 21x21 Casilleros que únicamente tiene
 * una Base en el centro.
 *
 * @author Mariano Tugnarelli
 * @prioridad 8
 */
public class DesiertoEspacial extends PartidaEspacialConBaseCentral {

    /**
     * @post Partida iniciada con la Base en el Casillero central [0][0].
     */
    public DesiertoEspacial() {

        super(new TableroDesierto());
    }

    /**
     * @return nombre de la Partida Espacial.
     * @post devuelve el nombre que identifica el juego.
     */
    @Override
    public String obtenerNombre() {

        return "Desierto Espacial";
    }
}
