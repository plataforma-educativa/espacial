package espacial.interfaz.componentes;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DibujarCasilleroBorde {

    private static final Paint COLOR_MARGEN = Color.web("4e4d50");
    private static final double DIMENSION = DibujarCasilleroInterior.DIMENSION;

    public Node ejecujar() {

        Rectangle margen = new Rectangle(DIMENSION, DIMENSION);
        margen.setFill(COLOR_MARGEN);
        margen.setStroke(COLOR_MARGEN);
        margen.setStrokeWidth(6);

        return margen;
    }
}
