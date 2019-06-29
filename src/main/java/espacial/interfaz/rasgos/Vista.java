package espacial.interfaz.rasgos;

import espacial.excepciones.Defecto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public interface Vista {

    Controlador crearControladr(Class<?> clase);

    default Parent cargar(String ruta) {

        URL recurso = cargarRecurso(ruta);

        try {

            FXMLLoader cargador = new FXMLLoader(recurso);
            cargador.setControllerFactory(this::crearControladr);
            return cargador.load();

        } catch (Exception e) {

            throw new Defecto("No fue posible cargar la vista: '" + ruta + "'", e);
        }
    }

    default URL cargarRecurso(String ruta) {

        URL recurso = getClass().getResource(ruta);

        if (recurso == null) {

            throw new Defecto("No fue posible cargar el recurso: '" + ruta + "'");
        }

        return recurso;
    }

    default Image cargarImagen(String ruta) {

        URL localizacion = cargarRecurso("/img/espacial.png");

        try {

            return new Image(localizacion.openStream());

        } catch (IOException e) {

            throw new Defecto("No fue posible crear imagen: '" + ruta + "'");
        }
    }
}
