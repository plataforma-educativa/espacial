package espacial.interfaz;

import espacial.Pieza;
import espacial.interfaz.rasgos.Controlador;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;

public abstract class ControladorDeInformePieza implements Controlador {

    @FXML
    protected TitledPane panel;
    
    protected final Pieza pieza;

    public ControladorDeInformePieza(Pieza unaPieza) {

        pieza = unaPieza;
    }

    @FXML
    void initialize() {
        
        completar();
    }
    
    protected abstract void completar();
}
