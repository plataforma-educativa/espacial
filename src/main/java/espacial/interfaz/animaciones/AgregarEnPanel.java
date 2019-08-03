package espacial.interfaz.animaciones;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class AgregarEnPanel extends Transition {

    private final Pane panel;
    private final Node nodo;

    public AgregarEnPanel(Pane unPanel, Node unNodo) {

        panel = unPanel;
        nodo = unNodo;
    }

    @Override
    protected void interpolate(double fraccion) {

        panel.getChildren().add(nodo);
    }
}
