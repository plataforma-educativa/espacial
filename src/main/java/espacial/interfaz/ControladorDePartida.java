package espacial.interfaz;

import espacial.NaveEspacial;
import espacial.PartidaEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.interfaz.componentes.Dibujante;
import espacial.interfaz.componentes.PanelConTablero;
import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import espacial.utiles.Proveedor;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;
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

    private final Visitante crearVistaInforme = new Visitante() {

        private final ObservableMap<Pieza, VistaInforme> vistaInformePorPieza = FXCollections.observableHashMap();

        {
            vistaInformePorPieza.addListener(this::alAgregarUnaVistaInforme);
        }

        private void alAgregarUnaVistaInforme(Change<? extends Pieza, ? extends Vista> cambio) {

            panelMarco.setRight(panelMarcoInformes);
        }

        @Override
        public void siEsNave(NaveEspacial pieza) {

            agregarVistaSiNoExiste(pieza, () -> new VistaInformeNave(panelInformes, pieza).iniciar());
        }

        @Override
        public void siEsBase(Pieza pieza) {

            agregarVistaSiNoExiste(pieza, () -> new VistaInformeBase(panelInformes, pieza).iniciar());
        }

        private void agregarVistaSiNoExiste(Pieza pieza, Proveedor<VistaInforme> proveedorDeVista) {

            vistaInformePorPieza.computeIfAbsent(pieza, p -> proveedorDeVista.obtener()).seleccionar();
        }

    };

    public ControladorDePartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
    }

    @FXML
    void initialize() {

        panelMarcoTablero.setContent(new PanelConTablero(this, partida.obtenerTablero()));
        panelMarco.setRight(null);
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

        unaPieza.aceptar(crearVistaInforme);
    }

    public void reproducir(Animation animacion) {

        Platform.runLater(animacion::play);
    }

    public Dibujante conDibujante() {

        return dibujante;
    }
}
