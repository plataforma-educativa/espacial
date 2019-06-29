package espacial.interfaz;

import espacial.interfaz.rasgos.Controlador;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

import java.net.URL;

public class ControladorDeDocumentacion implements Controlador {

    @FXML
    private WebView visorWeb;

    @FXML
    void initialize() {

        URL url = cargarRecurso("/documentacion/contratos.html");

        visorWeb.getEngine().load(url.toString());
    }
}
