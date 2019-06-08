package espacial.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnunciadoImplicito implements Condicion.Enunciado {

    private static final Pattern PATRON_METODO_LAMBDA = Pattern.compile(".+\\$(.*)\\$\\d+");

    private final Humanizador humanizador = new Humanizador();

    @Override
    public String describirFallo(Throwable excepcion) {

        String metodo = obtenerNombreDelMetodoQueLanzo(excepcion);
        String descripcion = extraerDescripcionDe(metodo);

        return humanizador.interpretar(descripcion);
    }

    private String obtenerNombreDelMetodoQueLanzo(Throwable excepcion) {

        String metodo = "";
        StackTraceElement[] traza = excepcion.getStackTrace();

        for (int i = 1; i < traza.length; i++) {
            if (Condicion.class.getName().equals(traza[i].getClassName())) {
                metodo = traza[i-1].getMethodName();
            }
        }

        return metodo;
    }

    private String extraerDescripcionDe(String metodo) {

        Matcher coincidencia = PATRON_METODO_LAMBDA.matcher(metodo);

        return coincidencia.matches() ? coincidencia.group(1) : "condición";
    }
}
