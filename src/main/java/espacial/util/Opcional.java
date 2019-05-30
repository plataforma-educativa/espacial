package espacial.util;

import espacial.Proveedor;

public class Opcional<T> {

    private static final Opcional<?> VACIO = new Opcional<>(null);

    private final T valor;

    private Opcional(T valor) {

        this.valor = valor;
    }

    @SuppressWarnings("unchecked")
    public static <T> Opcional<T> vacio() {

        return (Opcional<T>) VACIO;
    }

    public static <T> Opcional<T> con(T valor) {

        return new Opcional<>(valor);
    }

    public <X extends Throwable> T obtenerPeroSiNoExisteLanzar(Proveedor<X> crearExcepcion) throws X {

        if (valor == null) {

            throw crearExcepcion.obtener();
        }

        return valor;
    }
}
