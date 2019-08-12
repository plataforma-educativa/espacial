package espacial.utiles;

@FunctionalInterface
public interface ProveedorCon<T, P> {

    T crear(P parametro);
}
