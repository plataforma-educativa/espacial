package espacial.interfaz;

import espacial.PartidaEspacial;
import espacial.interfaz.componentes.PanelConTablero;
import espacial.interfaz.rasgos.Controlador;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;


public class ControladorDePartida implements Controlador {

    private final PartidaEspacial partida;

    @FXML
    private ScrollPane panelMarcoTablero;

    public ControladorDePartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
    }

    @FXML
    void initialize() {

        panelMarcoTablero.setContent(new PanelConTablero(partida.obtenerTablero()));

        centrarTablero();
    }

    private void centrarTablero() {

        panelMarcoTablero.setHvalue(panelMarcoTablero.getHmin() + panelMarcoTablero.getHmax() / 2);
        panelMarcoTablero.setVvalue(panelMarcoTablero.getVmin() + panelMarcoTablero.getVmax() / 2);
    }


    public void mostrarDocumentacion() {

        new VistaDocumentacion().iniciar();
    }
}
