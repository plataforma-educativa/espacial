package espacial;

import espacial.excepciones.LaOperacionNoEstaSoportada;

public interface Deposito {

    default void cargarAntimateria(int cantidad) {

        throw new LaOperacionNoEstaSoportada("cargarAntimateria(int)");
    }

    default void cargarMetal(int cantidad) {

        throw new LaOperacionNoEstaSoportada("cargarMetal(int)");
    }

    default void cargarCristal(int cantidad) {

        throw new LaOperacionNoEstaSoportada("cargarCristal(int)");
    }

    default void descargarAntimateria(int cantidad) {

        throw new LaOperacionNoEstaSoportada("descargarAntimateria(int)");
    }

    default void descargarMetal(int cantidad) {

        throw new LaOperacionNoEstaSoportada("descargarMetal(int)");
    }

    default void descargarCristal(int cantidad) {

        throw new LaOperacionNoEstaSoportada("descargarCristal(int)");
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
