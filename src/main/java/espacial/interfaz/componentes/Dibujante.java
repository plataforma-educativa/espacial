
package espacial.interfaz.componentes;

import espacial.Pieza;
import espacial.Visitante;
import javafx.scene.Node;

public class Dibujante implements Visitante {

    private final DibujarCasilleroBorde dibujarCasilleroBorde = new DibujarCasilleroBorde();
    private final DibujarCasilleroInterior dibujarCasilleroInterior = new DibujarCasilleroInterior();
    private final DibujarImagen dibujarImagen = new DibujarImagenPorTipo();

    /**
     * @post construye un dibujo que representa la Pieza dada.
     *
     * @param unaPieza Pieza a dibujar
     * @return representación de la pieza
     */
    public Node dibujar(Pieza unaPieza) {

        return dibujarImagen.de(unaPieza);
    }

    public Node dibujarMargen() {

        return dibujarCasilleroBorde.ejecujar();
    }

    public Node dibujarCasillero(int fila, int columna) {

        return dibujarCasilleroInterior.ejecutar(fila, columna);
    }
}
