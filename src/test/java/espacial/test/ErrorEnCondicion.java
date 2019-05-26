package espacial.test;

import java.io.PrintStream;

public class ErrorEnCondicion extends AssertionError {

    private static final long serialVersionUID = 4799228836923502253L;

    private Condicion.Enunciado enunciado;
    private Throwable origen;

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
