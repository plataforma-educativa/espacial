import espacial.Espacial;
import espacial.Interfaz;
import org.junit.jupiter.api.BeforeAll;

public class TestDeBatallaEspacial {

    private static final Interfaz INTERFAZ_SIMULADA = partida -> { };

    @BeforeAll
    static void configurarInterfaz() {

        Espacial.usar(() -> INTERFAZ_SIMULADA);
    }
}
