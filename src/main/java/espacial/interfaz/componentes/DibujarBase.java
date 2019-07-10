package espacial.interfaz.componentes;

import espacial.Pieza;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class DibujarBase extends Dibujar implements DibujarImagen {

    private static final String CUBIERTA = "M26.242,18.541c-0.424,0-0.763,0.338-0.763,0.76v4.766l-1.411,1.41H19.26c-0.421,0-0.76,0.34-0.76,0.762v3.953h-2V26.24c0-0.422-0.339-0.762-0.76-0.762h-4.805l-1.415-1.412V19.26c0-0.422-0.339-0.76-0.76-0.76H4.485v-2h4.275c0.42,0,0.76-0.34,0.76-0.76v-4.807l1.415-1.414h4.805c0.42,0,0.76-0.338,0.76-0.76V4.807h2V8.76c0,0.422,0.339,0.76,0.76,0.76h4.809l1.408,1.414v4.807c0,0.42,0.34,0.76,0.763,0.76h4.275v2.041H26.242z";

    private final Paint PINTURA = DibujarNave.PINTURA;

    @Override
    public Node de(Pieza unaPieza) {

        Rectangle casco = new Rectangle(4, 4, DibujarEspacio.DIMENSION - 8, DibujarEspacio.DIMENSION - 8);
        casco.setFill(PINTURA);
        casco.setStroke(conBorde());
        casco.setStrokeWidth(1);
        casco.setArcHeight(8);
        casco.setArcWidth(8);
        casco.setEffect(conSombraExterior());

        SVGPath cubierta = new SVGPath();
        cubierta.setFill(PINTURA);
        cubierta.setBlendMode(BlendMode.MULTIPLY);
        cubierta.setContent(CUBIERTA);
        cubierta.setLayoutX(4);
        cubierta.setLayoutY(4);

        cubierta.setEffect(conSombraInterior());

        return new Group(casco, cubierta);
    }
}
