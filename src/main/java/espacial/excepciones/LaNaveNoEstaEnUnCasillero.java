package espacial.excepciones;

public class LaNaveNoEstaEnUnCasillero extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 915399678995724747L;

    public LaNaveNoEstaEnUnCasillero() {
        
        super("La Nave no está en un Casillero del Tablero");
    }
}
