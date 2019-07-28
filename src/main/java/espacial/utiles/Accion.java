package espacial.utiles;

@FunctionalInterface
public interface Accion {

    Accion NINGUNA = () -> {};

    void ejecutar();
}
