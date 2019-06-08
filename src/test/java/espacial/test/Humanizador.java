package espacial.test;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Humanizador {

    public String interpretar(String descripcion) {

        return Stream
                .of(separarPalabrasEn(descripcion))
                .map(removiendoMayusculas())
                .collect(concatenandoConEspacios());
    }

    public String interpretar(Class<?> clase) {

        return titular(interpretar(clase.getSimpleName()));
    }

    public String interpretar(Method metodo) {

        return interpretar(metodo.getName());
    }

    private String titular(String oracion) {

        return StringUtils.capitalize(oracion);
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
