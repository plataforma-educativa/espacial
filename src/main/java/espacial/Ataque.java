package espacial;

/**
 * Un Ataque es una acción aplicada sobre un elemento Atacable.
 *
 * @author Mariano Tugnarelli
 */
public interface Ataque {

    void aplicarSobre(Atacable atacable);
}
