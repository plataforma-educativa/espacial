package espacial.interfaz.componentes.dibujos;

import espacial.Casillero;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DibujoEspacialDeCasillero extends Rectangle implements DibujoEspacial, Casillero.Visitante {

    private static final Paint COLOR_BORDE = Color.web("4e4d50");
    private static final Paint COLOR_CASILLERO_PAR = Color.web("dddddd");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("888888");

    public DibujoEspacialDeCasillero(Casillero casillero) {

        super(DIMENSION, DIMENSION);
        casillero.aceptar(this);
    }

    @Override
    public void siEsBorde(Casillero casillero) {

        setFill(COLOR_BORDE);
        setStrokeWidth(0);
    }

    @Override
    public void siEsInterior(Casillero casillero) {

        Paint pinturaCasillero = esParElNumeroDel(casillero) ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;
        setFill(pinturaCasillero);
        setStrokeWidth(0);
    }

    private boolean esParElNumeroDel(Casillero casillero) {

        return casillero.obtenerCoordenadas().numerar() % 2 == 0;
    }
}
