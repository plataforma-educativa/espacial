package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;

/**
 * Una Pieza es un elemento que participa de una Partida.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Pieza extends Obstaculo {

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
    
    @Override
    default void fueChocadaPor(PiezaMovil piezaMovil) {

        throw new LaOperacionNoEstaSoportada("Obstaculo.fueChocadaPor(PiezaMovil)");
    }

    default void chocarCon(Obstaculo obstaculo) {
        
        throw new LaOperacionNoEstaSoportada("Pieza.chocoCon(Obstaculo)");
    }

    void aceptar(Visitante visitante);
}
