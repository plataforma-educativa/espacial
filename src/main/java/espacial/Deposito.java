package espacial;

import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;

public interface Deposito {

    default void cargarAntimateria(int cantidad) {

        throw new NoPuedeRecibirUnaCarga(SustanciaEspacial.ANTIMATERIA.por(cantidad));
    }

    default void cargarMetal(int cantidad) {

        throw new NoPuedeRecibirUnaCarga(SustanciaEspacial.METAL.por(cantidad));
    }

    default void cargarCristal(int cantidad) {

        throw new NoPuedeRecibirUnaCarga(SustanciaEspacial.CRISTAL.por(cantidad));
    }

    default void descargarAntimateria(int cantidad) {

        throw new NoPuedeEntregarUnaCarga(SustanciaEspacial.ANTIMATERIA.por(cantidad));
    }

    default void descargarMetal(int cantidad) {

        throw new NoPuedeEntregarUnaCarga(SustanciaEspacial.METAL.por(cantidad));
    }

    default void descargarCristal(int cantidad) {

        throw new NoPuedeEntregarUnaCarga(SustanciaEspacial.CRISTAL.por(cantidad));
    }

    default int contarAntimateria() {

        return 0;
    }

    default int contarMetal() {

        return 0;
    }

    default int contarCristal() {

        return 0;
    }
}
