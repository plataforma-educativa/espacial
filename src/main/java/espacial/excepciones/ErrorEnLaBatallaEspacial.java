package espacial.excepciones;

/**
 * Excepción base para todas los Errores (RuntimeException) del juego.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ErrorEnLaBatallaEspacial extends RuntimeException {

    private static final long serialVersionUID = 7082586363556448877L;

    public ErrorEnLaBatallaEspacial() {
        super();
    }

    public ErrorEnLaBatallaEspacial(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ErrorEnLaBatallaEspacial(String mensaje) {
        super(mensaje);
    }

    public ErrorEnLaBatallaEspacial(Throwable causa) {
        super(causa);
    }
}
