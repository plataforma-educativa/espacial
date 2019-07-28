package espacial.interfaz;

import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.application.Platform;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

public abstract class VistaInforme implements Vista {

    private final Controlador controlador;
    private final Pane contenedor;
    private TitledPane informe;

    public VistaInforme(Pane enContenedor, Controlador conControlador) {

        contenedor = enContenedor;
        controlador = conControlador;
    }

    public VistaInforme iniciar() {

        informe = cargar(conInforme());

        contenedor.getChildren().add(informe);

        return this;
    }

    public void seleccionar() {

        Platform.runLater(this::expandirCapturandoFoco);
    }

    private void expandirCapturandoFoco() {

        informe.setExpanded(true);
        informe.requestFocus();
    }

    @Override
    public Controlador crearControlador(Class<?> clase) {

        return controlador;
    }

    protected abstract String conInforme();
}
