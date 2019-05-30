package espacial.test;

public class ErrorEnCondicion extends AssertionError {

    private static final long serialVersionUID = 4799228836923502253L;

    private final Condicion.Enunciado enunciado;
    private final Throwable origen;

    public ErrorEnCondicion(Condicion.Enunciado enunciado, Throwable origen) {

        this.enunciado = enunciado;
        this.origen = origen;
        setStackTrace(origen.getStackTrace());
    }

    @Override
    public String getMessage() {

        return new StringBuilder(enunciado.describirFallo(origen))
                    .append("\n")
                    .append(origen.getClass().getName())
                    .append(": ")
                    .append(origen.getMessage())
                    .toString();
    }
}
