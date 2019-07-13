package espacial.interfaz;

import espacial.PartidaEspacial;
import espacial.Pieza;
import espacial.interfaz.componentes.PanelConTablero;
import espacial.interfaz.rasgos.Controlador;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ControladorDePartida implements Controlador {

    private final PartidaEspacial partida;

    @FXML
    private ScrollPane panelMarcoTablero;

    @FXML
    private ScrollPane panelMarcoInformes;

    @FXML
    private VBox panelInformes;

    public ControladorDePartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
    }

    @FXML
    void initialize() {

        panelMarcoTablero.setContent(new PanelConTablero(this, partida.obtenerTablero()));
        centrarTablero();
    }

    private void centrarTablero() {

        panelMarcoTablero.setHvalue(panelMarcoTablero.getHmin() + panelMarcoTablero.getHmax() / 2);
        panelMarcoTablero.setVvalue(panelMarcoTablero.getVmin() + panelMarcoTablero.getVmax() / 2);
    }

    public void mostrarDocumentacion() {

        new VistaDocumentacion().iniciar();
    }

    public void fueSeleccionada(Pieza unaPieza) {

        VistaInformeNave vista = new VistaInformeNave(panelInformes, unaPieza);
        vista.iniciar();
    }

}
