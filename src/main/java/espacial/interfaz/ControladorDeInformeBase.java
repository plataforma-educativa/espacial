package espacial.interfaz;

import espacial.Ataque;
import espacial.BaseEspacial;
import espacial.Casillero;
import espacial.Obstaculo;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class ControladorDeInformeBase extends ControladorDeInformePieza<BaseEspacial> implements Pieza.Observador {

    @FXML
    private ProgressBar nivelDefensas;

    @FXML
    private Label defensasActivas;

    @FXML
    private Label defensasDestruidas;

    @FXML
    private TextField antimateria;

    @FXML
    private TextField cristal;

    @FXML
    private TextField metal;

    public ControladorDeInformeBase(BaseEspacial unaPieza) {

        super(unaPieza);
        unaPieza.registrar(this);
    }

    @Override
    protected void configurar() {

        defensasActivas.visibleProperty().bind(panel.getContent().disabledProperty().not());
        defensasDestruidas.visibleProperty().bind(defensasActivas.visibleProperty().not());
    }

    @Override
    protected void completar() {

        nivelDefensas.setProgress(pieza.obtenerNivelDeDefensas() / 100.0);
        antimateria.setText(comoTexto(pieza.buscar(SustanciaEspacial.ANTIMATERIA)));
        cristal.setText(comoTexto(pieza.buscar(SustanciaEspacial.CRISTAL)));
        metal.setText(comoTexto(pieza.buscar(SustanciaEspacial.METAL)));
    }

    private String comoTexto(int cantidad) {

        return String.valueOf(cantidad);
    }

    private void deshabilitar() {

        panel.getContent().setDisable(true);
    }

    @Override
    public void fueMovida(Pieza unaPieza, Casillero aCasillero) {

    }

    @Override
    public void fueAtacada(Pieza unaPieza, Ataque conAtaque) {

    }

    @Override
    public void fueChocada(Pieza unaPieza, Obstaculo conObstaculo) {

    }

    @Override
    public void cambioElEstadoDe(Pieza unaPieza) {

        Platform.runLater(this::completar);
    }

    @Override
    public void fueDestruida(Pieza unaPieza) {

        Platform.runLater(this::deshabilitar);
    }
}
