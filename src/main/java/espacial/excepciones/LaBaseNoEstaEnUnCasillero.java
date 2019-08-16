package espacial.excepciones;

public class LaBaseNoEstaEnUnCasillero extends ErrorEspacial {

    private static final long serialVersionUID = 915899178995204747L;

    private static final String MENSAJE = "La Base no está en un Casillero del Tablero";

    public LaBaseNoEstaEnUnCasillero() {

        super(MENSAJE);
    }
}
