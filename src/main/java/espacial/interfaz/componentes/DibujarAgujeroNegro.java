package espacial.interfaz.componentes;

import espacial.Pieza;
import javafx.scene.Group;
import javafx.scene.Node;

public class DibujarAgujeroNegro extends Dibujar implements DibujarImagen {

    @Override
    public Node de(Pieza unaPieza) {

        return new Group();
    }
}
