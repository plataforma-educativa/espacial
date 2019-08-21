package espacial.interfaz;

import espacial.Interfaz;
import espacial.Partida;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class InterfazFX extends Application implements Interfaz {

    private static final CountDownLatch cuentaRegresiva = new CountDownLatch(1);

    @Override
    public void start(Stage escenario) {

        fueLanzada();
    }

    @Override
    public void mostrar(Partida partida) {

        iniciar();
        crearVistaPara(partida);
    }

    private void crearVistaPara(Partida partida) {

        final CountDownLatch espera = new CountDownLatch(1);
        Platform.runLater(() -> {
            new VistaPartida(partida).iniciar(); espera.countDown();
        });
        try {
            espera.await();
        } catch (InterruptedException e) {

        }
    }

    private void iniciar() {

        synchronized(InterfazFX.class) {

            if (noFueLanzadaPreviamente()) {

                lanzar();
                esperarLanzamiento();
            }
        }
    }

    private void fueLanzada() {

        cuentaRegresiva.countDown();
    }

    private boolean noFueLanzadaPreviamente() {

        return cuentaRegresiva.getCount() > 0;
    }

    private void esperarLanzamiento() {

        try {

            cuentaRegresiva.await();

        } catch (InterruptedException ignorada) {
        }
    }

    private void lanzar() {

        new Thread(() -> launch()).start();
    }

}
