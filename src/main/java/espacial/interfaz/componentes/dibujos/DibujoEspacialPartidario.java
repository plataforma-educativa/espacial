package espacial.interfaz.componentes.dibujos;

import espacial.Partidario;
import javafx.scene.paint.Paint;

public interface DibujoEspacialPartidario extends DibujoEspacial, Partidario.Condicional {

    Paint PINTURA_ALIADO = javafx.scene.paint.Color.web("00AA22FF");
    Paint PINTURA_NEUTRAL = javafx.scene.paint.Color.web("666666FF");
    Paint PINTURA_RIVAL = javafx.scene.paint.Color.web("BB0022FF");

    void cambiarPinturaPor(Paint nuevaPintura);

    @Override
    default void siEsAliado() {

        cambiarPinturaPor(PINTURA_ALIADO);
    }

    @Override
    default void siEsNeutral() {

        cambiarPinturaPor(PINTURA_NEUTRAL);
    }

    @Override
    default void siEsRival() {

        cambiarPinturaPor(PINTURA_RIVAL);
    }
}
