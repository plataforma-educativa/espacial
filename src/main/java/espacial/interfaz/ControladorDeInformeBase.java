package espacial.interfaz;

import espacial.Pieza;
import espacial.SustanciaEspacial;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorDeInformeBase extends ControladorDeInformePieza<Pieza> {

    @FXML
    private TextField antimateria;

    @FXML
    private TextField cristal;

    @FXML
    private TextField metal;

    public ControladorDeInformeBase(Pieza unaPieza) {

        super(unaPieza);
    }

    @Override
    protected void configurar() {

    }

    @Override
    protected void completar() {

        antimateria.setText(comoTexto(pieza.buscar(SustanciaEspacial.ANTIMATERIA)));
        cristal.setText(comoTexto(pieza.buscar(SustanciaEspacial.CRISTAL)));
        metal.setText(comoTexto(pieza.buscar(SustanciaEspacial.METAL)));
    }

    private String comoTexto(int cantidad) {

        return String.valueOf(cantidad);
    }
}
