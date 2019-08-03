package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.VisitanteDeCasilleros;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DibujarEspacio extends Dibujar implements VisitanteDeCasilleros {

    public static final double DIMENSION = 42.0;
    private static final Paint COLOR_BORDE = Color.web("4e4d50");
    private static final Paint COLOR_CASILLERO_PAR = Color.web("dddddd");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("888888");

    private Node dibujo;

    @Override
    public void siEsBorde(Casillero casillero) {

        Rectangle margen = new Rectangle(DIMENSION, DIMENSION);
        margen.setFill(COLOR_BORDE);
        margen.setStrokeWidth(0);

        dibujo = margen;
    }

    @Override
    public void siEsInterior(Casillero casillero) {

        Rectangle rectanguloCasillero = new Rectangle(DIMENSION, DIMENSION);

        Paint pinturaCasillero = esParElNumeroDel(casillero) ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;
        rectanguloCasillero.setFill(pinturaCasillero);
        rectanguloCasillero.setStrokeWidth(0);

        dibujo = new StackPane(rectanguloCasillero, numeroDe(casillero));
    }

    private boolean esParElNumeroDel(Casillero casillero) {

        return casillero.obtenerCoordenadas().numerar() % 2 == 0;
    }

    private Node numeroDe(Casillero casillero) {

        return new Text(String.valueOf(casillero.obtenerCoordenadas().numerar()));
    }

    public Node de(Casillero casillero) {

        dibujo = null;

        casillero.aceptar(this);

        return dibujo;
    }
}
