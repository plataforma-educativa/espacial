package espacial.interfaz;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class Aplicacion extends Application {

    private static final CountDownLatch cuentaRegresiva = new CountDownLatch(1);

    @Override
    public void start(Stage escenario) {

        cuentaRegresiva.countDown();
    }

    public static void iniciar() {

        try {

            if (cuentaRegresiva.getCount() > 0) {

                new Thread(() -> launch()).start();
                cuentaRegresiva.await();
            }

        } catch (InterruptedException ignorada) {
        }
    }
}
