package espacial.interfaz;

import espacial.Interfaz;
import espacial.Partida;
import espacial.utiles.Expectativa;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class InterfazFX extends Application implements Interfaz {

    private static final Expectativa fxIniciado = new Expectativa();

    @Override
    public void start(Stage escenario) {

        fxIniciado.cumplir();
    }

    @Override
    public void mostrar(Partida partida) {

        iniciar();
        crearVistaPara(partida);
    }

    private void crearVistaPara(Partida partida) {

        final Expectativa vistaIniciada = new Expectativa();

        Platform.runLater(() -> {
            new VistaPartida(partida).iniciar();
            vistaIniciada.cumplir();
        });

        vistaIniciada.esperar();
    }

    private void iniciar() {

        fxIniciado.esperar(this::lanzar);
    }

    private void lanzar() {

        Thread procesador = new Thread(() -> launch());
        procesador.setContextClassLoader(this.getClass().getClassLoader());
        procesador.start();
    }

}
