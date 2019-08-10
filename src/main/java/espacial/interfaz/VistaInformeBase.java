package espacial.interfaz;

import espacial.BaseEspacial;
import javafx.scene.layout.Pane;

public class VistaInformeBase extends VistaInforme {

    public VistaInformeBase(Pane enContenedor, BaseEspacial unaPieza) {

        super(enContenedor, new ControladorDeInformeBase(unaPieza));
    }

    @Override
    protected String conInforme() {

        return "/fx/informe-base.fxml";
    }
}
