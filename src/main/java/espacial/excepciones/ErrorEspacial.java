package espacial.excepciones;

/**
 * Excepción base para todas los Errores (RuntimeException) del juego.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ErrorEspacial extends RuntimeException {

    private static final long serialVersionUID = 7082586363556448877L;

    public ErrorEspacial() {

        super();
    }

    public ErrorEspacial(String mensaje, Throwable causa) {

        super(mensaje, causa);
    }

    public ErrorEspacial(String mensaje, Object... argumentos) {

        super(String.format(mensaje, argumentos));
    }

    public ErrorEspacial(Throwable causa) {

        super(causa);
    }
}
