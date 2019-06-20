package espacial.utiles;

public interface Referencia<T> {

    T obtener();

    void cambiar(T valor);

    void siEsNuloAlObtener(Proveedor<T> operacion);

    static <T> Referencia<T> conValor(T valor) {

        return new ReferenciaRequerida<>(valor);
    }

    static <T> Referencia<T> conValorNulo() {

        return new ReferenciaRequerida<>(null);
    }

}
