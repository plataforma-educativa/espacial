package espacial.utiles;

@FunctionalInterface
public interface AccionSobre<T> {

    AccionSobre NINGUNA = parametro -> {};

    void ejecutar(T parametro);
}
