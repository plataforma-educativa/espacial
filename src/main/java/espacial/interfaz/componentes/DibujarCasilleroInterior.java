package espacial.interfaz.componentes;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DibujarCasilleroInterior {

    private static final Paint COLOR_CASILLERO_PAR = Color.web("e6e7e8");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("444444");
    public static final double DIMENSION = 30.0;

    public Node ejecutar(int fila, int columna) {

        Rectangle casillero = new Rectangle(DIMENSION, DIMENSION);

        Paint pinturaCasillero = (fila + columna) % 2 == 0 ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;
        casillero.setFill(pinturaCasillero);
        casillero.setStroke(pinturaCasillero);
        casillero.setStrokeWidth(6);

        return casillero;

    }
}
