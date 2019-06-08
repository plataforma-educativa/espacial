package espacial.test;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class NombrarTestDeContrato implements DisplayNameGenerator {

    private final Humanizador humanizador = new Humanizador();

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {

        return humanizador.interpretar(testClass);
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {

        return humanizador.interpretar(nestedClass);
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {

        return humanizador.interpretar(testMethod);
    }
}
