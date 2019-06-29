package espacial.interfaz.componentes;

import espacial.Pieza;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class DibujarBase implements DibujarImagen {

    private static final String CASCO = "M34.761,4.977l-4.74-4.738C29.879,0.086,29.679,0,29.469,0H5.529c-0.208,0-0.41,0.086-0.552,0.239L0.239,4.977C0.087,5.121,0,5.322,0,5.531v23.936c0,0.211,0.087,0.41,0.239,0.557l4.738,4.736C5.122,34.912,5.324,35,5.532,35h23.937c0.21,0,0.41-0.088,0.555-0.24l4.737-4.74C34.913,29.877,35,29.678,35,29.467V5.529C35,5.32,34.913,5.119,34.761,4.977z";
    private static final String CUBIERTA = "M26.242,18.541c-0.424,0-0.763,0.338-0.763,0.76v4.766l-1.411,1.41H19.26c-0.421,0-0.76,0.34-0.76,0.762v3.953h-2V26.24c0-0.422-0.339-0.762-0.76-0.762h-4.805l-1.415-1.412V19.26c0-0.422-0.339-0.76-0.76-0.76H4.485v-2h4.275c0.42,0,0.76-0.34,0.76-0.76v-4.807l1.415-1.414h4.805c0.42,0,0.76-0.338,0.76-0.76V4.807h2V8.76c0,0.422,0.339,0.76,0.76,0.76h4.809l1.408,1.414v4.807c0,0.42,0.34,0.76,0.763,0.76h4.275v2.041H26.242z";

    private final Paint PINTURA = DibujarNave.PINTURA;

    @Override
    public Node de(Pieza unaPieza) {

        SVGPath casco = new SVGPath();
        casco.setFill(PINTURA);
        casco.setContent(CASCO);

        SVGPath cubierta = new SVGPath();
        cubierta.setFill(PINTURA);
        cubierta.setBlendMode(BlendMode.MULTIPLY);
        cubierta.setContent(CUBIERTA);

        InnerShadow sombra = new InnerShadow();
        sombra.setOffsetY(1.0);
        sombra.setOffsetX(1.0);
        sombra.setWidth(3.0);
        sombra.setHeight(3.0);
        sombra.setColor(Color.BLACK);
        sombra.setRadius(2.0);
        cubierta.setEffect(sombra);

        return new Group(casco, cubierta);
    }
}
