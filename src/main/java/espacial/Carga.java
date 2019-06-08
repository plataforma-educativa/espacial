package espacial;

public interface Carga {

    int obtenerCantidad();

    void subirEn(Deposito unDeposito);

    void bajarDe(Deposito unDeposito);
}
