package espacial.interfaz.componentes;

import espacial.Casillero;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DibujarCasilleroInterior {

    private static final Paint COLOR_CASILLERO_PAR = Color.web("e6e7e8");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("444444");
    public static final double DIMENSION = 30.0;

    public Node ejecutar(Casillero casillero) {

        Rectangle rectanguloCasillero = new Rectangle(DIMENSION, DIMENSION);

        Paint pinturaCasillero = (casillero.obtenerNumero()) % 2 == 0 ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;
        rectanguloCasillero.setFill(pinturaCasillero);
        rectanguloCasillero.setStroke(pinturaCasillero);
        rectanguloCasillero.setStrokeWidth(6);

        return rectanguloCasillero;

    }
}
