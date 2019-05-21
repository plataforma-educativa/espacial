package espacial;

@FunctionalInterface
public interface Accion {

    Accion NINGUNA = () -> {};

    void ejecutar();
}
