package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.VisitanteDeCasilleros;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DibujarEspacio implements VisitanteDeCasilleros {

    private static final double DIMENSION = 30.0;
    private static final Paint COLOR_BORDE = Color.web("4e4d50");
    private static final Paint COLOR_CASILLERO_PAR = Color.web("e6e7e8");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("444444");

    private Node dibujo;

    @Override
    public void siEsBorde(Casillero casillero) {

        Rectangle margen = new Rectangle(DIMENSION, DIMENSION);
        margen.setFill(COLOR_BORDE);
        margen.setStroke(COLOR_BORDE);
        margen.setStrokeWidth(6);

        dibujo = margen;
    }

    @Override
    public void siEsInterior(Casillero casillero) {

        Rectangle rectanguloCasillero = new Rectangle(DIMENSION, DIMENSION);

        Paint pinturaCasillero = esParElNumeroDel(casillero) ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;
        rectanguloCasillero.setFill(pinturaCasillero);
        rectanguloCasillero.setStroke(pinturaCasillero);
        rectanguloCasillero.setStrokeWidth(6);

        dibujo = rectanguloCasillero;
    }

    private boolean esParElNumeroDel(Casillero casillero) {

        return casillero.obtenerCoordenadas().numerar() % 2 == 0;
    }

    public Node de(Casillero casillero) {

        dibujo = null;

        casillero.aceptar(this);

        return dibujo;
    }
}
