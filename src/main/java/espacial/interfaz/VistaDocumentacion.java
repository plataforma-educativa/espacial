package espacial.interfaz;

import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaDocumentacion implements Vista {

    private final Stage escenario;

    public VistaDocumentacion() {

        escenario = new Stage(StageStyle.DECORATED);
        escenario.setTitle("Documentación");
    }

    public void iniciar() {

        Scene escena = new Scene(cargar("/fx/documentacion.fxml"));
        escenario.setScene(escena);
        escenario.show();
    }

    @Override
    public Controlador crearControladr(Class<?> clase) {

        return new ControladorDeDocumentacion();
    }
}
