package espacial.interfaz;

import espacial.Casillero;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorDeInformeBase extends ControladorDeInformePieza<Pieza> implements Pieza.Observador {

    @FXML
    private TextField antimateria;

    @FXML
    private TextField cristal;

    @FXML
    private TextField metal;

    public ControladorDeInformeBase(Pieza unaPieza) {

        super(unaPieza);
        unaPieza.registrar(this);
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

    @Override
    public void fueMovida(Pieza unaPieza, Casillero aCasillero) {

    }

    @Override
    public void cambioElEstadoDe(Pieza unaPieza) {

        Platform.runLater(this::completar);
    }

    @Override
    public void fueDestruida(Pieza unaPieza) {

    }
}
