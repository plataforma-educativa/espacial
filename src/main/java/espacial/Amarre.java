package espacial;

/**
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Amarre {

    /**
     * @post suelta el Amarre, liberando la Pieza.
     */
    void soltar();
    
    /**
     * @return Pieza que ha sido amarrada.
     */
    PiezaMovil obtenerPieza();
}
