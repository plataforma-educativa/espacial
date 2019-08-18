package espacial.interfaz.componentes.dibujos;

import espacial.Casillero;
import espacial.Coordenadas;
import espacial.Tablero;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DibujoEspacialDeCasillero extends StackPane implements DibujoEspacial, Casillero.Visitante {

    private static final Paint COLOR_BORDE = Color.web("4e4d50");
    private static final Paint COLOR_BORDE_TEXTO = Color.web("999999");
    private static final Paint COLOR_CASILLERO_PAR = Color.web("dddddd");
    private static final Paint COLOR_CASILLERO_IMPAR = Color.web("888888");

    public DibujoEspacialDeCasillero(Casillero casillero) {

        super();
        casillero.aceptar(this);
    }

    @Override
    public void siEsBorde(Casillero casillero) {

        Rectangle fondo = new Rectangle(DIMENSION, DIMENSION);
        fondo.setFill(COLOR_BORDE);
        fondo.setStrokeWidth(0);

        Text texto = new Text();
        texto.setText(obtenerTextoDeBorde(casillero));
        texto.setFill(COLOR_BORDE_TEXTO);

        getChildren().addAll(fondo, texto);
    }

    @Override
    public void siEsInterior(Casillero casillero) {

        Paint pinturaCasillero = esParElNumeroDel(casillero) ? COLOR_CASILLERO_PAR : COLOR_CASILLERO_IMPAR;

        Rectangle fondo = new Rectangle(DIMENSION, DIMENSION);
        fondo.setFill(pinturaCasillero);
        fondo.setStrokeWidth(0);

        getChildren().add(fondo);
    }

    private boolean esParElNumeroDel(Casillero casillero) {

        return casillero.obtenerCoordenadas().numerar() % 2 == 0;
    }

    private String obtenerTextoDeBorde(Casillero casillero) {

        String coordenada;

        Coordenadas coordenadas = casillero.obtenerCoordenadas();
        Tablero tablero = casillero.obtenerTablero();

        int columna = coordenadas.obtenerColumna();
        int fila = coordenadas.obtenerFila();

        if (estaEnRango(tablero.obtenerColumnaMinima(), columna, tablero.obtenerColumnaMaxima())) {

            coordenada = String.valueOf(columna);

        } else if (estaEnRango(tablero.obtenerFilaMinima(), fila, tablero.obtenerFilaMaxima())) {

            coordenada = String.valueOf(fila);

        } else {

            coordenada = "";
        }

        return coordenada;
    }

    private boolean estaEnRango(int minimo, int valor, int maximo) {

        return valor >= minimo && valor <= maximo;
    }

}
