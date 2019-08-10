
package espacial.interfaz.componentes;

import espacial.Casillero;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.interfaz.componentes.dibujos.DibujoDeAgujeroNegro;
import espacial.interfaz.componentes.dibujos.DibujoEspacialDeAsteroide;
import espacial.interfaz.componentes.dibujos.DibujoEspacialDeBase;
import espacial.interfaz.componentes.dibujos.DibujoEspacialDeCasillero;
import espacial.interfaz.componentes.dibujos.DibujoEspacialDeContenedor;
import espacial.interfaz.componentes.dibujos.DibujoEspacialDeNave;
import javafx.scene.Node;

public class Dibujante implements Visitante {

    private Node dibujo;

    /**
     * @post construye un dibujo que representa la Pieza dada.
     *
     * @param unaPieza Pieza a dibujar
     * @return representación de la pieza
     */
    public Node dibujar(Pieza unaPieza) {

        dibujo = null;

        unaPieza.aceptar(this);

        return dibujo;
    }

    /**
     * @post construye un dibujo que representa el Casillero dado.
     *
     * @param casillero Casillero a dibujar
     * @return representación del casillero
     */
    public Node dibujar(Casillero casillero) {

        return new DibujoEspacialDeCasillero(casillero);
    }

    @Override
    public void siEsNave(NaveEspacial pieza) {

        dibujo = new DibujoEspacialDeNave(pieza);
    }

    @Override
    public void siEsBase(Pieza pieza) {

        dibujo = new DibujoEspacialDeBase(pieza);
    }

    @Override
    public void siEsAsteroide(Pieza pieza) {

        dibujo = new DibujoEspacialDeAsteroide(pieza);
    }

    @Override
    public void siEsContenedor(Pieza pieza) {

        dibujo = new DibujoEspacialDeContenedor(pieza);
    }

    @Override
    public void siEsAgujeroNegro(Pieza pieza) {

        dibujo = new DibujoDeAgujeroNegro();
    }
}
