package espacial.interfaz.componentes.dibujos;

import espacial.Pieza;
import espacial.utiles.Aleatorio;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.SVGPath;

public class DibujoEspacialDeAsteroide extends Group implements DibujoEspacial {

    private static final Aleatorio<Paint> PINTURA_ASTEROIDE_SUPERFICIE = Aleatorio.enLista(
            Color.web("504100"),
            Color.web("403100"),
            Color.web("594909"),
            Color.web("605120"),
            Color.web("554110")
    );

    private static final Aleatorio<Paint> PINTURA_ASTEROIDE_CRATER = Aleatorio.enLista(
            Color.web("333333"),
            Color.web("444444"),
            Color.web("555555"),
            Color.web("666666"),
            Color.web("777777")
    );

    private static final String CUERPO = "M32.495,16.372c-0.096-1.128-0.555-2.351-1.214-3.592c-0.711-1.338-4.192-6.084-4.845-6.978c-0.974-1.333-1.615-2.363-1.439-2.868c0.259-0.751-2.625-0.417-5.776,0.155c-1.218,0.222-3.503-0.41-3.071,1.267c0.433,1.675-4.446,0.34-4.446,0.34s-5.501,1.718-4.503,2.75c0.609,0.633-4.748,7.37-4.7,8.921c0.139,4.239,2.907,10.175,3.008,8.913c0.098-1.26,3.011,3.024,4.492,4.523c1.693,1.712,10.246,1.347,10.093,2.396s6.058-0.899,4.905-2.396c-1.306-1.689,4.431-5.439,3.537-6.6C27.641,22.05,32.682,18.657,32.495,16.372z";
    private static final String CRATER_3 = "M14.699,19.507c-0.791,0-1.432-0.66-1.432-1.48c0-0.817,0.641-1.478,1.432-1.478c0.787,0,1.427,0.66,1.427,1.478S15.486,19.507,14.699,19.507z";
    private static final String CRATER_4 = "M12.876,13.559c-0.942,1.17-2.292,1.611-3.011,0.991c-0.724-0.624-0.542-2.078,0.399-3.246c0.946-1.17,2.296-1.614,3.016-0.991C14.004,10.936,13.822,12.389,12.876,13.559z";
    private static final String CRATER_5 = "M26.819,15.82c0.577,0,1.047,0.486,1.047,1.082c0,0.602-0.47,1.087-1.047,1.087s-1.049-0.489-1.049-1.087C25.771,16.306,26.242,15.82,26.819,15.82z";
    private static final String CRATER_6 = "M20.778,11.917c0-1.1,1.126-1.992,2.518-1.992c1.385,0,2.517,0.892,2.517,1.992c0,1.096-1.132,1.988-2.517,1.988C21.904,13.905,20.778,13.013,20.778,11.917z";


    public DibujoEspacialDeAsteroide(Pieza unaPieza) {

        conCuerpo();
        conCrateres();
        setLayoutX(3);
        setLayoutY(2);
    }

    private void conCuerpo() {

        SVGPath cuerpo = new SVGPath();
        cuerpo.setFill(PINTURA_ASTEROIDE_SUPERFICIE.obtener());
        cuerpo.setContent(CUERPO);
        aplicarBorde(cuerpo);
        aplicarSombraExteriorEn(cuerpo);

        getChildren().add(cuerpo);
    }

    private void conCrateres() {

        Ellipse crater1 = new Ellipse(7.759, 20.256, 0.859, 0.886);
        crater1.setFill(PINTURA_ASTEROIDE_CRATER.obtener());
        crater1.setBlendMode(BlendMode.COLOR_DODGE);

        Ellipse crater2 = new Ellipse(19.623, 25.974, 2.518, 2.603);
        crater2.setFill(PINTURA_ASTEROIDE_CRATER.obtener());
        crater2.setBlendMode(BlendMode.COLOR_DODGE);

        SVGPath crater3 = new SVGPath();
        crater3.setFill(PINTURA_ASTEROIDE_CRATER.obtener());
        crater3.setContent(CRATER_3);
        crater3.setBlendMode(BlendMode.COLOR_DODGE);

        SVGPath crater4 = new SVGPath();
        crater4.setFill(PINTURA_ASTEROIDE_CRATER.obtener());
        crater4.setContent(CRATER_4);
        crater4.setBlendMode(BlendMode.COLOR_DODGE);

        SVGPath crater5 = new SVGPath();
        crater5.setFill(PINTURA_ASTEROIDE_CRATER.obtener());
        crater5.setContent(CRATER_5);
        crater5.setBlendMode(BlendMode.COLOR_DODGE);

        SVGPath crater6 = new SVGPath();
        crater6.setFill(PINTURA_ASTEROIDE_CRATER.obtener());
        crater6.setContent(CRATER_6);
        crater6.setBlendMode(BlendMode.COLOR_DODGE);

        getChildren().addAll(crater1, crater2, crater3, crater4, crater5, crater6);
    }
}
