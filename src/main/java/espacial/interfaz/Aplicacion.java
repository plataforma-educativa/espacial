package espacial.interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

    private final FXMLLoader cargador;

    public Aplicacion() {

        cargador = new FXMLLoader(getClass().getResource("/fx/principal.fxml"));
    }

    @Override
    public void start(Stage escenario) throws Exception {

        Pane panel = cargador.load();
        Scene escena = new Scene(panel, 400, 300);

        escenario.setMinWidth(720);
        escenario.setMinHeight(600);
        escenario.getIcons().add(new Image(getClass().getResourceAsStream("/img/espacial.png")));
        escenario.setTitle("Espacial");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {

        Application.launch(args);
    }
}
