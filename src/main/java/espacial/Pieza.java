package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.excepciones.NoPuedeEntregarUnaCarga;

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
     * @param unaSustancia Sustancia buscada en la Pieza.
     * @return cantidad de {@code unaSustancia} que existe dentro de la Pieza.
     */
    default int buscar(SustanciaEspacial unaSustancia) {

        return 0;
    }

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

    /**
     * @pre  la Pieza es capaz de recibir una Carga de SustanciaEspacial.
     * @post toma la Carga indicada.
     * @param unaCarga Carga de Sustancia Espacial.
     */
    void recibir(Carga unaCarga);

    /**
     * @pre  la Pieza es capaz de entregar una Carga de SustanciaEspacial.
     * @post entrega la Carga indicada.
     * @param unaCarga Carga de Sustancia Espacial a entregar.
     */
    default void entregar(Carga unaCarga) {

        throw new NoPuedeEntregarUnaCarga(this, unaCarga);
    }
}
