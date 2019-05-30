package espacial.utiles;

@FunctionalInterface
public interface Proveedor<T> {

    T obtener();
}
