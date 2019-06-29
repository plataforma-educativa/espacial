package espacial.interfaz.componentes;

import espacial.Pieza;
import espacial.Visitante;
import javafx.scene.Node;

public class DibujarImagenPorTipo implements DibujarImagen, Visitante {

    private final DibujarImagen dibujarNave = new DibujarNave();
    private final DibujarImagen dibujarBase = new DibujarBase();
    private final DibujarImagen dibujarAsteroide = new DibujarAsteroide();
    private final DibujarImagen dibujarContenedor = new DibujarContenedor();
    private final DibujarImagen dibujarAgujeroNegro = new DibujarAgujeroNegro();

    private Node dibujo;

    @Override
    public Node de(Pieza unaPieza) {

        dibujo = null;

        unaPieza.aceptar(this);

        return dibujo;
    }

    @Override
    public void siEsNave(Pieza pieza) {

        dibujo = dibujarNave.de(pieza);
    }

    @Override
    public void siEsBase(Pieza pieza) {

        dibujo = dibujarBase.de(pieza);
    }

    @Override
    public void siEsAsteroide(Pieza pieza) {

        dibujo = dibujarAsteroide.de(pieza);
    }

    @Override
    public void siEsContenedor(Pieza pieza) {

        dibujo = dibujarContenedor.de(pieza);
    }

    @Override
    public void siEsAgujeroNegro(Pieza pieza) {

        dibujo = dibujarAgujeroNegro.de(pieza);
    }
}
