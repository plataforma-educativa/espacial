package espacial.interfaz.componentes;

import espacial.Pieza;
import espacial.interfaz.ControladorDePartida;
import javafx.scene.Cursor;
import javafx.scene.Group;

public class PanelConPieza extends Group {

    private final ControladorDePartida controlador;

    private final Dibujante dibujante;

    private final Pieza pieza;

    public PanelConPieza(ControladorDePartida unControlador, Dibujante unDibujante, Pieza unaPieza) {

        controlador = unControlador;
        dibujante = unDibujante;
        pieza = unaPieza;
        getChildren().add(dibujante.dibujar(pieza));
        setCursor(Cursor.HAND);
        setOnMousePressed(event -> unControlador.fueSeleccionada(pieza));
    }
}
