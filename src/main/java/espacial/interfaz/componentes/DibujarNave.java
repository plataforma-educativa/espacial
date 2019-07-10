package espacial.interfaz.componentes;

import espacial.Pieza;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class DibujarNave extends Dibujar implements DibujarImagen {

    public static final Paint PINTURA = Color.web("00AA22FF");

    private static final String CASCO = "M4.477,24.277c0,0,1.084,5.091,1.402,5.489c0.126,0.158,0.368,0.345,0.802,0.345c0.214,0,2.511-0.548,2.511-0.548l7.611,2.938c0,0,1.316-0.008,1.504,0l7.611-2.915c0,0,2.08,0.523,2.291,0.523c0.455,0,0.69-0.216,0.791-0.346c0.308-0.403,1.521-5.506,1.521-5.506s0.312,0.212,0.5,0.212c0.084,0,0.158-0.017,0.219-0.038c0.135-0.021,0.296-0.079,0.449-0.226c0.408-0.388,0.867-1.457,0.804-6.313l-0.002-0.231c0-2.223-0.286-11.386-1.654-11.386c-0.313,0-0.594,0.121-0.812,0.344c-0.489,0.509-0.559,8.034-0.546,8.7l-3.126-0.498c-0.169-0.396-0.329-0.781-0.479-1.148c-0.933-2.298-3.7-8.389-4.397-8.812c-0.117-0.07-3.86-2.359-4.024-2.359c-0.154,0-0.293-0.006-0.32,0c-0.031,0.008-3.663,2.308-3.692,2.322c-0.257,0.116-3.299,5.317-4.789,8.989c-0.151,0.374-0.314,0.767-0.485,1.169L5.521,15.32c0.011-0.645-0.062-8.125-0.539-8.622C4.763,6.473,4.48,6.353,4.163,6.353c-1.367,0-1.654,9.14-1.654,11.346l-0.003,0.23c-0.062,4.843,0.396,5.907,0.804,6.293c0.155,0.148,0.317,0.207,0.453,0.23C3.957,24.512,4.477,24.277,4.477,24.277z";
    private static final String PUENTE = "M12.164,14.809c0.021-0.063,0.532-1.553,1.709-4.24c1.104-2.518,3.054-2.712,3.625-2.712c0.14,0,0.22,0.011,0.22,0.011c3.629,0.05,5.071,6.703,5.131,6.985c0.026,0.125-0.009,0.255-0.098,0.347c-0.088,0.103-0.223,0.157-0.361,0.157l0,0l0,0h-9.777l0,0c-0.149,0-0.289-0.062-0.377-0.173C12.146,15.075,12.12,14.937,12.164,14.809z";
    private static final String ESCOTILLA = "M18.036,18.005v5.423c0,0.276-0.241,0.502-0.536,0.502c-0.296,0-0.536-0.226-0.536-0.502v-5.423c0-0.279,0.24-0.505,0.536-0.505C17.795,17.5,18.036,17.728,18.036,18.005z";

    public Node de(Pieza unaPieza) {

        SVGPath casco = new SVGPath();
        casco.setFill(PINTURA);
        casco.setContent(CASCO);
        casco.setStroke(conBorde());
        casco.setEffect(conSombraExterior());

        SVGPath puente = new SVGPath();
        puente.setFill(PINTURA);
        puente.setBlendMode(BlendMode.MULTIPLY);
        puente.setContent(PUENTE);
        puente.setEffect(conSombraInterior());

        SVGPath escotilla = new SVGPath();
        escotilla.setFill(PINTURA);
        escotilla.setBlendMode(BlendMode.MULTIPLY);
        escotilla.setContent(ESCOTILLA);
        escotilla.setEffect(conSombraInterior());

        return new Group(casco, puente, escotilla);
    }
}
