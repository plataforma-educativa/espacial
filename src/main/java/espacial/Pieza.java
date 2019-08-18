package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;

/**
 * Una Pieza es un elemento que participa de una Partida.
 *
 * @author Mariano Tugnarelli
 */
public interface Pieza extends Obstaculo, Objetivo, Partidario {

    int PUNTOS_MINIMOS = 1;
    int PUNTOS_MAXIMOS = 1000;

    /**
     * @return el EspectroEspacial que la Pieza refleja en el Radar de los
     * participantes.
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
     * @param casillero
     * @pre la Pieza fue colocada (inicialmente o luego de ser movida) en el
     * {@code casillero} de un Tablero.
     */
    default void fueColocadaEn(Casillero casillero) {

    }

    default void chocarCon(Obstaculo obstaculo) {

        throw new LaOperacionNoEstaSoportada("Pieza.chocoCon(Obstaculo)");
    }

    void aceptar(Visitante visitante);

    /**
     * @return la cantidad de puntos que le permiten a la Pieza continuar en la Partida.
     */
    int obtenerPuntos();

    /**
     * @param unaCarga Carga de Sustancia Espacial.
     * @pre la Pieza sera capaz de recibir una Carga de SustanciaEspacial.
     * @post toma la Carga indicada.
     */
    default void recibir(Carga unaCarga) {

        throw new NoPuedeRecibirUnaCarga(this, unaCarga);
    }

    /**
     * @param unaCarga Carga de Sustancia Espacial a entregar.
     * @pre la Pieza sera capaz de entregar una Carga de SustanciaEspacial.
     * @post entrega la Carga indicada.
     */
    default void entregar(Carga unaCarga) {

        throw new NoPuedeEntregarUnaCarga(this, unaCarga);
    }

    default String describir() {

        return "Pieza<" + escanear() + ">";
    }

    default void registrar(Observador observador) {

    }

    interface Observador {

        default void cambioElEstadoDe(Pieza unaPieza) {
        }

        default void fueMovida(Pieza unaPieza, Casillero aCasillero) {
        }

        default void fueAtacada(Pieza unaPieza, Ataque conAtaque) {
        }

        default void fueChocada(Pieza unaPieza, Obstaculo contraObstaculo) {
        }

        default void fueDestruida(Pieza unaPieza) {
        }
    }

    interface Visitante {

        default void siEsBase(BaseEspacial pieza) {
        }

        default void siEsNave(NaveEspacial pieza) {
        }

        default void siEsContenedor(Pieza pieza) {
        }

        default void siEsAsteroide(Pieza pieza) {
        }

        default void siEsAgujeroNegro(Pieza pieza) {
        }
    }
}
