package espacial.interfaz;

import espacial.Pieza;
import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class VistaInformeBase implements Vista {

    private final ControladorDeInformePieza controlador;
    private final VBox contenedor;

    public VistaInformeBase(VBox enContenedor, Pieza unaPieza) {

        controlador = new ControladorDeInformeBase(unaPieza);
        contenedor = enContenedor;
    }

    public Vista iniciar() {

        Parent informe = cargar("/fx/informe-base.fxml");

        contenedor.getChildren().add(informe);

        return this;
    }

    @Override
    public Controlador crearControlador(Class<?> clase) {

        return controlador;
    }
}
