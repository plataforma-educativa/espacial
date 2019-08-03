package espacial.interfaz;

import espacial.PartidaEspacial;
import espacial.Pieza;
import espacial.interfaz.animaciones.Reproducir;
import espacial.interfaz.componentes.Dibujante;
import espacial.interfaz.componentes.PanelConTablero;
import espacial.interfaz.rasgos.Controlador;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ControladorDePartida implements Controlador {

    private final PartidaEspacial partida;

    private final Dibujante dibujante = new Dibujante();

    @FXML
    private BorderPane panelMarco;

    @FXML
    private ScrollPane panelMarcoTablero;

    @FXML
    private ScrollPane panelMarcoInformes;

    @FXML
    private VBox panelInformes;

    private VistaListaInformes vistaListaInformes;

    public ControladorDePartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
    }

    @FXML
    void initialize() {

        vistaListaInformes = new VistaListaInformes(panelInformes, this::mostrarInformes);
        panelMarcoTablero.setContent(new PanelConTablero(this, partida.obtenerTablero()));
        panelMarco.setRight(null);
        centrarTablero();
    }

    private void centrarTablero() {

        panelMarcoTablero.setHvalue(panelMarcoTablero.getHmin() + panelMarcoTablero.getHmax() / 2);
        panelMarcoTablero.setVvalue(panelMarcoTablero.getVmin() + panelMarcoTablero.getVmax() / 2);
    }

    private void mostrarInformes(VistaInforme vistaInforme) {

        panelMarco.setRight(panelMarcoInformes);
    }

    public void mostrarDocumentacion() {

        new VistaDocumentacion().iniciar();
    }

    public void fueSeleccionada(Pieza unaPieza) {

        vistaListaInformes.mostrarInformePara(unaPieza);
    }

    public void reproducir(Animation... animaciones) {

        this.reproducir(new SequentialTransition(animaciones));
    }

    public void reproducir(Animation animacion) {

        new Reproducir(animacion).esperando();
    }

    public Dibujante conDibujante() {

        return dibujante;
    }
}
