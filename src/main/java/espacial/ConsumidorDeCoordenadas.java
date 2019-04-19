package espacial;

/**
 * Visitor para iterar todas las Coordenadas de un Tablero.
 * 
 * @author Mariano Tugnarelli
 *
 */
@FunctionalInterface
public interface ConsumidorDeCoordenadas {

    void aceptar(int fila, int columna);
}
