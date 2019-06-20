package espacial.excepciones;

public class LaNaveNoEstaEnUnCasillero extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 915399678995724747L;

    private static final String MENSAJE = "La Nave no está en un Casillero del Tablero";

    public LaNaveNoEstaEnUnCasillero() {
        
        super(MENSAJE);
    }

    public LaNaveNoEstaEnUnCasillero(String justificacion) {

        super(MENSAJE + " " + justificacion);
    }
}
