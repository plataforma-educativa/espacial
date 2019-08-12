package espacial.interfaz;

import espacial.PartidaEspacial;
import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaPartida implements Vista {

    private final Stage escenario;
    private final PartidaEspacial partida;
    private final int anchoInicial = 800;
    private final int altoInicial = 600;

    public VistaPartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
        escenario = new Stage(StageStyle.DECORATED);
        escenario.setMinWidth(anchoInicial);
        escenario.setMinHeight(altoInicial);
        escenario.getIcons().add(cargarImagen("/img/espacial.png"));
        escenario.setTitle(partida.obtenerNombre());
    }

    public void iniciar() {

        escenario.setScene(new Scene(cargar("/fx/partida.fxml"), anchoInicial, altoInicial));
        escenario.show();
        escenario.setOnCloseRequest(evento -> terminar());
    }

    private void terminar() {

        Platform.exit();
    }

    @Override
    public Controlador crearControlador(Class<?> clase) {

        return new ControladorDePartida(partida);
    }
}
