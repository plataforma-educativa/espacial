package espacial.partidas;

import espacial.PartidaEspacial;
import espacial.Tablero;
import espacial.interfaz.Aplicacion;
import espacial.interfaz.VistaPartida;
import javafx.application.Platform;

public class PartidaEspacialFX implements PartidaEspacial {

    private final Tablero tablero;

    public PartidaEspacialFX(Tablero tableroUsado) {

        Aplicacion.iniciar();
        tablero = tableroUsado;
    }

    public void iniciar() {

        Platform.runLater(() -> {

            VistaPartida vistaPartida = new VistaPartida(this);
            vistaPartida.iniciar();
        });
    }

    @Override
    public String obtenerNombre() {

        return "Batalla Espacial";
    }

    @Override
    public Tablero obtenerTablero() {

        return tablero;
    }
}
