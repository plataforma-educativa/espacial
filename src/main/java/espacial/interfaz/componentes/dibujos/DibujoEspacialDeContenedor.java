package espacial.interfaz.componentes.dibujos;

import espacial.Pieza;
import espacial.SustanciaEspacial;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class DibujoEspacialDeContenedor extends Group implements DibujoEspacial {

    private static final Color PINTURA_ESTRUCTURA = Color.web("555555");
    private static final Paint PINTURA_INDICADOR_VACIO = Color.WHITE;
    private static final Paint PINTURA_INDICADOR_ANTIMATERIA = Color.web("ffe35b");
    private static final Paint PINTURA_INDICADOR_METAL = Color.web("dd1539");
    private static final Paint PINTURA_INDICADOR_CRISTAL = Color.web("3daeb6");

    private static final String ESTRUCTURA = "M28,4H8C6.9,4,6,4.9,6,6v23.001c0,1.1,0.9,2,2,2h20c1.101,0,2-0.9,2-2V6C30,4.9,29.101,4,28,4z";

    private Paint colorIndicador;

    public DibujoEspacialDeContenedor(Pieza unaPieza) {

        colorIndicador = obtenerColorDeSustanciaEn(unaPieza);;
        conEstructura();
        conIndicadores();

        setLayoutX(3);
        setLayoutY(3);
    }

    private void conEstructura() {

        SVGPath estructura = new SVGPath();
        estructura.setFill(PINTURA_ESTRUCTURA);
        estructura.setContent(ESTRUCTURA);
        aplicarBorde(estructura);
        aplicarSombraExteriorEn(estructura);

        getChildren().addAll(estructura);
    }

    private void conIndicadores() {

        Rectangle indicador1 = new Rectangle(10, 8, 16, 4);
        Rectangle indicador2 = new Rectangle(10, 15, 16, 4);
        Rectangle indicador3 = new Rectangle(10, 22, 16, 4);

        indicador1.setFill(colorIndicador);
        indicador2.setFill(colorIndicador);
        indicador3.setFill(colorIndicador);

        aplicarSombraInteriorEn(indicador1);
        aplicarSombraInteriorEn(indicador2);
        aplicarSombraInteriorEn(indicador3);

        getChildren().addAll(indicador1, indicador2, indicador3);
    }

    private Paint obtenerColorDeSustanciaEn(Pieza unaPieza) {

        Paint pintura;

        int antimateria = unaPieza.buscar(SustanciaEspacial.ANTIMATERIA);
        int metal = unaPieza.buscar(SustanciaEspacial.METAL);
        int cristal = unaPieza.buscar(SustanciaEspacial.CRISTAL);

        if ((antimateria > 0) || (metal > 0) || (cristal > 0)) {

            if ((antimateria >= metal) && (antimateria >= cristal)) {

                pintura = PINTURA_INDICADOR_ANTIMATERIA;

            } else if ((metal >= antimateria) && (metal >= cristal)) {

                pintura = PINTURA_INDICADOR_METAL;

            } else {

                pintura = PINTURA_INDICADOR_CRISTAL;
            }

        } else {

            pintura = PINTURA_INDICADOR_VACIO;
        }

        return pintura;
    }
}
