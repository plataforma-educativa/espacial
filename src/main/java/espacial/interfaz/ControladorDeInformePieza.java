package espacial.interfaz;

import espacial.Pieza;
import espacial.interfaz.rasgos.Controlador;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;

public abstract class ControladorDeInformePieza<T extends Pieza> implements Controlador {

    @FXML
    protected TitledPane panel;
    
    protected final T pieza;

    public ControladorDeInformePieza(T unaPieza) {

        pieza = unaPieza;
    }

    @FXML
    void initialize() {
        
        completar();
    }
    
    protected abstract void completar();
}
