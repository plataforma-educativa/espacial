package espacial.interfaz.componentes.dibujos;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface DibujoEspacial {

    double DIMENSION = 42.0;

    default void aplicarBorde(Shape dibujo) {

        dibujo.setStroke(Color.DARKGRAY);
        dibujo.setStrokeWidth(1);
    }

    default void aplicarSombraExteriorEn(Node dibujo) {

        DropShadow sombra = new DropShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);

        dibujo.setEffect(sombra);
    }

    default void aplicarSombraInteriorEn(Node dibujo) {

        InnerShadow sombra = new InnerShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);
        dibujo.setEffect(sombra);
    }
}
