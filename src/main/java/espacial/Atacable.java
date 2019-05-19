package espacial;

/**
 * Un Atacable es un elemento capaz de reaccionar ante diferentes tipos de ataque.
 *
 * @author Mariano Tugnarelli
 */
public interface Atacable {

    /**
     * @pre el Atacable fue atacado con torpedo de fotones.
     */
    default void atacadoConTorpedoDeFotones() {

    }

    /**
     * @pre el Atacable fue atacado con laser.
     */
    default void atacadoConLaser() {

    }

}
