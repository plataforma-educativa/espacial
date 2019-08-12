package espacial;

import espacial.partidas.EscenarioEspacial;
import espacial.partidas.PartidaEspacialFX;
import espacial.utiles.ProveedorCon;
import espacial.utiles.Referencia;

public interface PartidaEspacial {

    Referencia<ProveedorCon<PartidaEspacial, EscenarioEspacial>> FABRICA = Referencia.conValor(PartidaEspacialFX::new);

    String obtenerNombre();

    Tablero obtenerTablero();

    static void iniciar(EscenarioEspacial escenario) {
    
        FABRICA.obtener().crear(escenario);
    }
}
