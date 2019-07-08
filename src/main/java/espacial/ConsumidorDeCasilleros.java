package espacial;

/**
 * Visitor para iterar todos los Casilleros de un Tablero.
 * 
 * @author Mariano Tugnarelli
 *
 */
@FunctionalInterface
public interface ConsumidorDeCasilleros {

    void aceptar(Casillero casillero, Pieza... piezas);
}
