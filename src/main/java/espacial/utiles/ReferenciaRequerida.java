package espacial.utiles;

import espacial.excepciones.Defecto;

class ReferenciaRequerida<T> implements Referencia<T> {

    private T valor;

    private Proveedor<T> siValorEsNulo = () ->  { throw new Defecto("El valor requerido es nulo"); };

    ReferenciaRequerida() {

        valor = null;
    }

    ReferenciaRequerida(T valorRequerido) {

        valor = valorRequerido;
    }

    ReferenciaRequerida(Proveedor<T> generarValor) {

        valor = null;
        siValorEsNulo = generarValor;
    }

    @Override
    public T obtener() {

        return (valor != null) ? valor : siValorEsNulo.obtener();
    }

    @Override
    public void cambiar(T nuevoValor) {

        valor = nuevoValor;
    }

    @Override
    public void anular() {

        valor = null;
    }

    @Override
    public void siEsNuloAlObtener(Proveedor<T> usarProveedor) {

        siValorEsNulo = usarProveedor;
    }

    @Override
    public String toString() {

        return "Referencia -> " + (valor == null ? "NULO" : valor);
    }
}
