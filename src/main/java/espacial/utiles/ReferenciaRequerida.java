package espacial.utiles;

import espacial.excepciones.Defecto;

class ReferenciaRequerida<T> implements Referencia<T> {

    private T valor;

    private Proveedor<T> siValorEsNulo = () ->  { throw new Defecto("El valor requerido es nulo"); };

    ReferenciaRequerida(T valorRequerido) {

        valor = valorRequerido;
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
    public void siEsNuloAlObtener(Proveedor<T> crearExcepcion) {

        siValorEsNulo = crearExcepcion;
    }
}
