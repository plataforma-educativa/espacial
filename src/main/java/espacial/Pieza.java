package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;

/**
 * Una Pieza es un elemento que participa de una Partida.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Pieza extends Obstaculo, Objetivo {

    int PUNTOS_MINIMOS = 1;
    int PUNTOS_MAXIMOS = 1000;

    /**
     * @return el EspectroEspacial que la Pieza refleja en el Radar de los
     *         participantes.
     */
    EspectroEspacial escanear();

    /**
     * @pre la Pieza fue colocada (inicialmente o luego de ser movida) en el
     *      {@code casillero} de un Tablero.
     * @param casillero
     */
    default void fueColocadaEn(Casillero casillero) {

    }

    default void chocarCon(Obstaculo obstaculo) {

        throw new LaOperacionNoEstaSoportada("Pieza.chocoCon(Obstaculo)");
    }

    /**
     * @pre la Pieza fue atacada con {@code unAtaque}.
     * @param unAtaque
     */
    default void fueAtacadoCon(Ataque unAtaque) {

        throw new LaOperacionNoEstaSoportada("Pieza.fueAtacadoCon(Ataque)");
    }

    void aceptar(Visitante visitante);

    /**
     * @return la cantidad de puntos que le permiten a la Pieza continuar en la Partida.
     */
    int obtenerPuntos();
}
