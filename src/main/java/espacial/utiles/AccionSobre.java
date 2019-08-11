package espacial.utiles;

@FunctionalInterface
public interface AccionSobre<T> {

    static <P> AccionSobre<P> ninguna() {

        return parametro -> {};
    }

    void ejecutar(T parametro);
}
