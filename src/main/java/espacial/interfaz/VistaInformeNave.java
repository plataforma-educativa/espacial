package espacial.interfaz;

import espacial.Pieza;
import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class VistaInformeNave implements Vista {

    private final ControladorDeInformePieza controlador;
    private final VBox contenedor;

    public VistaInformeNave(VBox enContenedor, Pieza unaPieza) {

        controlador = new ControladorDeInformeNave(unaPieza);
        contenedor = enContenedor;
    }

    public Vista iniciar() {

        Parent informe = cargar("/fx/informe-nave.fxml");

        contenedor.getChildren().add(informe);

        return this;
    }

    @Override
    public Controlador crearControlador(Class<?> clase) {

        return controlador;
    }
}
