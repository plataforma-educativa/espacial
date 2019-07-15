package espacial.interfaz;

import espacial.NaveEspacial;
import espacial.PartidaEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.interfaz.componentes.PanelConTablero;
import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class ControladorDePartida implements Controlador {

    private final PartidaEspacial partida;

    private final Map<Pieza, Vista> vistaInformePorPieza;

    @FXML
    private BorderPane panelMarco;

    @FXML
    private ScrollPane panelMarcoTablero;

    @FXML
    private ScrollPane panelMarcoInformes;

    @FXML
    private VBox panelInformes;

    private final Visitante crearVistaInforme = new Visitante() {

        @Override
        public void siEsNave(NaveEspacial pieza) {

            if (! vistaInformePorPieza.containsKey(pieza)) {

                vistaInformePorPieza.put(pieza, crearVistaNave(pieza));
            }
        }

        private Vista crearVistaNave(NaveEspacial unaNave) {

            return new VistaInformeNave(panelInformes, unaNave).iniciar();
        }

        @Override
        public void siEsBase(Pieza pieza) {

            if (! vistaInformePorPieza.containsKey(pieza)) {

                vistaInformePorPieza.put(pieza, crearVistaBase(pieza));
            }
        }

        private Vista crearVistaBase(Pieza unaBase) {

            return new VistaInformeBase(panelInformes, unaBase).iniciar();
        }

    };


    public ControladorDePartida(PartidaEspacial unaPartida) {

        partida = unaPartida;
        vistaInformePorPieza = crearVistaInformePorPieza();
    }

    private Map<Pieza, Vista> crearVistaInformePorPieza() {

        ObservableMap<Pieza, Vista> mapa = FXCollections.observableHashMap();
        mapa.addListener((Change<? extends Pieza, ? extends Vista> cambio) -> panelMarco.setRight(panelMarcoInformes));

        return mapa;
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
}
