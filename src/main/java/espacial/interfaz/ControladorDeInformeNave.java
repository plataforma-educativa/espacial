package espacial.interfaz;

import espacial.Casillero;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.utiles.Nombre;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ControladorDeInformeNave extends ControladorDeInformePieza<NaveEspacial> implements Pieza.Observador {

    @FXML
    private Label nombre;

    @FXML
    private ProgressBar nivelEscudos;

    @FXML
    private Label escudosActivos;

    @FXML
    private Label escudosDestruidos;

    @FXML
    private TextField torpedos;

    @FXML
    private TextField antimateria;

    @FXML
    private TextField cristal;

    @FXML
    private TextField metal;

    public ControladorDeInformeNave(NaveEspacial unaPieza) {

        super(unaPieza);
        pieza.registrar(this);
    }

    @Override
    protected void configurar() {

        nombre.setText(pieza.nombrar().obtener());
        escudosActivos.visibleProperty().bind(panel.getContent().disabledProperty().not());
        escudosDestruidos.visibleProperty().bind(escudosActivos.visibleProperty().not());
    }

    @Override
    protected void completar() {

        nivelEscudos.setProgress(pieza.obtenerNivelDeEscudos() / 100.0);
        torpedos.setText(comoTexto(pieza.obtenerCantidadDeTorpedosDeFotones()));
        antimateria.setText(comoTexto(pieza.buscar(SustanciaEspacial.ANTIMATERIA)));
        cristal.setText(comoTexto(pieza.buscar(SustanciaEspacial.CRISTAL)));
        metal.setText(comoTexto(pieza.buscar(SustanciaEspacial.METAL)));
    }

    private String comoTexto(int cantidad) {

        return String.valueOf(cantidad);
    }

    public void mostrarExplicacion(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        Nombre nombreDeLaNave = pieza.nombrar();
        alert.setTitle("Nombre");
        alert.setHeaderText(nombreDeLaNave.obtener());

        TextArea explicacion = new TextArea(nombreDeLaNave.explicar());
        explicacion.setWrapText(true);
        explicacion.setMaxWidth(400);
        explicacion.setMaxHeight(100);
        explicacion.setEditable(false);

        alert.getDialogPane().setContent(new BorderPane(explicacion));
        alert.showAndWait();
    }

    @Override
    public void fueMovida(Pieza unaPieza, Casillero aCasillero) {

    }

    @Override
    public void cambioElEstadoDe(Pieza unaPieza) {

        Platform.runLater(this::completar);
    }

    @Override
    public void fueDestruida(Pieza unaPieza) {

        Platform.runLater(() -> panel.getContent().setDisable(true));
    }
}
