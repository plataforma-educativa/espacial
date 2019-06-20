package espacial.utiles;

public interface Referencia<T> {

    T obtener();

    void cambiar(T valor);
}
