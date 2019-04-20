package espacial;

@FunctionalInterface
public interface Proveedor<T> {

    T obtener();
}
