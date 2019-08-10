package espacial.interfaz.animaciones;

import espacial.excepciones.ErrorEspacial;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.util.concurrent.CountDownLatch;

public class Reproducir {

    private final CountDownLatch control = new CountDownLatch(1);
    private final Animation animacion;

    public Reproducir(Animation unaAnimacion) {

        animacion = unaAnimacion;
        animacion.setOnFinished(this::alTerminar);
    }

    private void alTerminar(ActionEvent actionEvent) {

        control.countDown();
    }

    public void esperando() {

        Platform.runLater(animacion::play);

        esperar();
    }

    private void esperar() {

        try {

            control.await();

        } catch (InterruptedException e) {

            throw new ErrorEspacial("Animación interrumpida", e);
        }
    }


}
