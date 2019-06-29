package espacial.interfaz.rasgos;

import espacial.excepciones.Defecto;

import java.net.URL;

public interface Controlador {

    default URL cargarRecurso(String ruta) {

        URL recurso = getClass().getResource(ruta);

        if (recurso == null) {

            throw new Defecto("No fue posible cargar el recurso: '" + ruta + "'");
        }

        return recurso;
    }
}
