package espacial.interfaz.componentes;

import espacial.Pieza;
import javafx.scene.Cursor;
import javafx.scene.Group;

public class PanelConPieza extends Group {

    private final Dibujante dibujante = new Dibujante();

    private final Pieza pieza;

    public PanelConPieza(Pieza unaPieza) {

        pieza = unaPieza;
        getChildren().add(dibujante.dibujar(pieza));
        setCursor(Cursor.HAND);
    }
}
