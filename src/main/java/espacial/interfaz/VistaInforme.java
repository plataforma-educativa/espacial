package espacial.interfaz;

import espacial.interfaz.rasgos.Controlador;
import espacial.interfaz.rasgos.Vista;
import javafx.application.Platform;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

public abstract class VistaInforme implements Vista {

    private final Controlador controlador;
    private final Pane contenedor;
    private final TitledPane informe;

    public VistaInforme(Pane enContenedor, Controlador conControlador) {

        contenedor = enContenedor;
        controlador = conControlador;

        informe = cargar(conInforme());
        contenedor.getChildren().add(informe);
    }

    public VistaInforme seleccionar() {

        Platform.runLater(this::expandirCapturandoFoco);

        return this;
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
