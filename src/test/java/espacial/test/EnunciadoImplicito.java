package espacial.test;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnunciadoImplicito implements Condicion.Enunciado {

    private static final Pattern PATRON_METODO_LAMBDA = Pattern.compile(".+\\$(.*)\\$\\d+");

    @Override
    public String describirFallo(Throwable excepcion) {

        String metodo = obtenerNombreDelMetodoQueLanzo(excepcion);
        String descripcion = extraerDescripcionDe(metodo);
        String[] palabras = separarPalabrasEn(descripcion);

        return Stream.of(palabras)
                     .map(removiendoMayusculas())
                     .collect(concatenandoConEspacios());
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

    private String[] separarPalabrasEn(String texto) {

        return StringUtils.splitByCharacterTypeCamelCase(texto);
    }

    private Collector<CharSequence, ?, String> concatenandoConEspacios() {

        return Collectors.joining(" ");
    }

    private Function<String, String> removiendoMayusculas() {

        return palabra -> StringUtils.isAllUpperCase(palabra) ? palabra : StringUtils.lowerCase(palabra);
    }

}
