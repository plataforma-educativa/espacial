package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;
import espacial.tableros.CasilleroInterior;

/**
 * Una Pieza es un elemento que participa de una Partida.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Pieza {

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
    default void fueColocadaEn(CasilleroInterior casillero) {

    }
    
    /**
     * @pre la Pieza fue chocada por {@code otrPieza}.
     * @param otraPieza
     */
    default void fueChocadaPor(PiezaMovil otraPieza) {
        
        throw new LaOperacionNoEstaSoportada("Pieza.fueChocadaPor(PiezaMovil)");
    }

    default void chocarCon(Pieza pieza) {
        
        throw new LaOperacionNoEstaSoportada("Pieza.chocoCon(Pieza)");
    }
}
