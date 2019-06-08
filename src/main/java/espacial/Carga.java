package espacial;

public interface Carga {

    int obtenerCantidad();

    void subirEn(Transporte unTransporte);

    void bajarDe(Transporte unTransporte);
}
