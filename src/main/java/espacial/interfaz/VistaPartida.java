package espacial.interfaz;

import espacial.PartidaEspacial;
import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaPartida implements Vista {

    private final Stage escenario;
    private final PartidaEspacial partida;

    public VistaPartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
        escenario = new Stage(StageStyle.DECORATED);
        escenario.setMinWidth(720);
        escenario.setMinHeight(600);
        escenario.getIcons().add(cargarImagen("/img/espacial.png"));
        escenario.setTitle(partida.obtenerNombre());
    }

    public void iniciar() {

        escenario.setScene(new Scene(cargar("/fx/partida.fxml"), 400, 300));
        escenario.show();
    }

    @Override
    public Controlador crearControlador(Class<?> clase) {

        return new ControladorDePartida(partida);
    }
}
