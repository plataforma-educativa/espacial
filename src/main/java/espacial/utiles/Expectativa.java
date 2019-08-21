package espacial.utiles;

import java.util.concurrent.CountDownLatch;

public class Expectativa {

    private CountDownLatch cuentaRegresiva = new CountDownLatch(1);

    public void cumplir() {

        cuentaRegresiva.countDown();
    }

    public void esperar() {

        try {

            cuentaRegresiva.await();

        } catch (InterruptedException ignorada) {

        }
    }

    public synchronized void esperar(Accion accion) {

        if (cuentaRegresiva.getCount() > 0) {

            accion.ejecutar();

            esperar();
        }
    }
}
