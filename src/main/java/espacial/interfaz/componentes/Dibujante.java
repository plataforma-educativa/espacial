
package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.Pieza;
import espacial.Visitante;
import javafx.scene.Node;

public class Dibujante implements Visitante {

    private final DibujarImagen dibujarImagen = new DibujarImagenPorTipo();
    private final DibujarEspacio dibujarEspacio = new DibujarEspacio();

    /**
     * @post construye un dibujo que representa la Pieza dada.
     *
     * @param unaPieza Pieza a dibujar
     * @return representación de la pieza
     */
    public Node dibujar(Pieza unaPieza) {

        return dibujarImagen.de(unaPieza);
    }

    /**
     * @post construye un dibujo que representa el Casillero dado.
     *
     * @param casillero Casillero a dibujar
     * @return representación del casillero
     */
    public Node dibujar(Casillero casillero) {

        return dibujarEspacio.de(casillero);
    }
}
