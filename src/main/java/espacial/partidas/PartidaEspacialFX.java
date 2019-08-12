package espacial.partidas;

import espacial.interfaz.AplicacionFX;
import espacial.interfaz.VistaPartida;
import javafx.application.Platform;

public class PartidaEspacialFX extends PartidaEnEscenarioEspacial {

    public PartidaEspacialFX(EscenarioEspacial escenarioUsado) {

        super(escenarioUsado);
        AplicacionFX.iniciar();
        Platform.runLater(this::crearVista);
    }

    private void crearVista() {

        new VistaPartida(this).iniciar();
    }
}
