package espacial;

/**
 * Un Objetivo es un elemento que puede recibir un Ataque.
 *
 * @author Mariano Tugnarelli
 */
public interface Objetivo {

    /**
     * @pre el Objetivo fue atacado con {@code ataque}.
     * @param ataque
     */
    void fueAtacadoCon(Ataque ataque);
}
