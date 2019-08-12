import espacial.PartidaEspacial;
import espacial.partidas.PartidaEspacialConsola;
import org.junit.jupiter.api.BeforeAll;

public class TestDeBatallaEspacial {

    @BeforeAll
    static void configurarPartida() {

        PartidaEspacial.FABRICA.cambiar(PartidaEspacialConsola::new);
    }
}
