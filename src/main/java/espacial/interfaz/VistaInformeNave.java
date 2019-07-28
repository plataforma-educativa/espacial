package espacial.interfaz;

import espacial.NaveEspacial;
import javafx.scene.layout.Pane;

public class VistaInformeNave extends VistaInforme {

    public VistaInformeNave(Pane enContenedor, NaveEspacial unaNave) {

        super(enContenedor, new ControladorDeInformeNave(unaNave));
    }

    @Override
    protected String conInforme() {

        return "/fx/informe-nave.fxml";
    }
}
