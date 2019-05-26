package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

public class AsteroideTest extends PruebaSobrePieza<Asteroide> {

    private Asteroide unAsteroide;

    @Override
    public Asteroide piezaCreada() {

        return new Asteroide();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.ASTEROIDE;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnAsteroide());
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnAsteroide());
        comprobarQue(losPuntosInicialesDeUnAsteroideSonCorrectos());
    }

    private Precondicion fueCreadoUnAsteroide() {

        return precondicion(() -> unAsteroide = new Asteroide());
    }

    private Postcondicion losPuntosInicialesDeUnAsteroideSonCorrectos() {

        return postcondicion(() -> assertThat(unAsteroide.obtenerPuntos()).as("puntos").isEqualTo(90));
    }
}
