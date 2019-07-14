package espacial.interfaz;

import espacial.NaveEspacial;
import espacial.utiles.Nombre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ControladorDeInformeNave extends ControladorDeInformePieza<NaveEspacial> {

    @FXML
    private TextField nombre;

    @FXML
    private ProgressBar nivelEscudos;

    @FXML
    private Label carga;
    
    @FXML
    private Label arsenal;

    public ControladorDeInformeNave(NaveEspacial unaPieza) {

        super(unaPieza);
    }

    @Override
    protected void completar() {

        Nombre nombreDeLaNave = pieza.nombrar();

        nombre.setText(nombreDeLaNave.obtener());
        nivelEscudos.setProgress(pieza.obtenerNivelDeEscudos() / 100.0);
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
}
