package espacial.interfaz;

import espacial.Pieza;
import javafx.scene.layout.Pane;

public class VistaInformeBase extends VistaInforme {

    public VistaInformeBase(Pane enContenedor, Pieza unaPieza) {

        super(enContenedor, new ControladorDeInformeBase(unaPieza));
    }

    @Override
    protected String conInforme() {

        return "/fx/informe-base.fxml";
    }
}
