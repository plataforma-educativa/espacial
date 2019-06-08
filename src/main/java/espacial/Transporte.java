package espacial;

public interface Transporte {

    default void cargarAntimateria(int cantidad) {

    }

    default void cargarMetal(int cantidad) {

    }

    default void cargarCristal(int cantidad) {

    }

    default void descargarAntimateria(int cantidad) {

    }

    default void descargarMetal(int cantidad) {

    }

    default void descargarCrital(int cantidad) {

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
