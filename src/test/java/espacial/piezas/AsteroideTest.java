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
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra un Asteroide", () -> {
            
            verify(PIEZA_MOVIL).chocoContraUnAsteroide();
        });
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnAsteroide());

        comprobarQue(losPuntosInicialesDeUnAsteroideSonCorrectos());
    }

    private Precondicion fueCreadoUnAsteroide() {

        return precondicion("fue creado unAsteroide", () -> {

            unAsteroide = new Asteroide();
        });
    }

    private Postcondicion losPuntosInicialesDeUnAsteroideSonCorrectos() {

        return postcondicion("los puntos iniciales de unAsteroide son correctos", () -> {

            assertThat(unAsteroide.obtenerPuntos()).as("puntos")
                    .isEqualTo(90);
        });
    }
}
