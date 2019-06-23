package espacial.partidas;

import espacial.interfaz.Aplicacion;
import javafx.application.Application;

public class PartidaEspacial {

    private final Thread hilo;

    public PartidaEspacial() {

        hilo = new Thread(() -> Application.launch(Aplicacion.class));
    }

    public void iniciar() {

        hilo.start();
    }
}
