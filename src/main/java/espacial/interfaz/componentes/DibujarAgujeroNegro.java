package espacial.interfaz.componentes;

import espacial.Pieza;
import javafx.scene.Group;
import javafx.scene.Node;

public class DibujarAgujeroNegro implements DibujarImagen {

    @Override
    public Node de(Pieza unaPieza) {

        return new Group();
    }
}
