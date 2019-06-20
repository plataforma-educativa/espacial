package espacial.utiles;

import espacial.excepciones.Defecto;

public class ReferenciaRequerida<T> implements Referencia<T> {

    private T valor;

    private Proveedor<? extends RuntimeException> valorEsNulo = () -> new Defecto("El valor requerido es nulo");

    public static <T> ReferenciaRequerida<T> conValor(T valor) {

        return new ReferenciaRequerida<>(valor);
    }

    public static <T> ReferenciaRequerida<T> conValorNulo() {

        return new ReferenciaRequerida<>(null);
    }

    private ReferenciaRequerida(T valorRequerido) {

        valor = valorRequerido;
    }

    @Override
    public T obtener() {

        if (valor == null) {

            throw valorEsNulo.obtener();
        }

        return valor;
    }

    @Override
    public void cambiar(T nuevoValor) {

        valor = nuevoValor;
    }

    public ReferenciaRequerida<T> siEsNuloAlObtener(Proveedor<? extends  RuntimeException> crearExcepcion) {

        valorEsNulo = crearExcepcion;

        return this;
    }

}
