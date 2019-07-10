package espacial.interfaz.componentes;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

public abstract class Dibujar {

    protected Color conBorde() {

        return Color.DARKGRAY;
    }

    protected Effect conSombraExterior() {

        DropShadow sombra = new DropShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);

        return sombra;
    }

    protected Effect conSombraInterior() {

        InnerShadow sombra = new InnerShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);

        return sombra;
    }
}
