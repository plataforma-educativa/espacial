package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.EspectroEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

public class ContenedorDeAntimateriaTest extends PruebaSobrePieza<ContenedorDeAntimateria> {

    private ContenedorDeAntimateria unContenedorDeAntimateria;

    @Override
    public ContenedorDeAntimateria piezaCreada() {

        return new ContenedorDeAntimateria();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.CONTENEDOR;
    }

    @Override
    public Postcondicion laPiezaMovilFueNotificadaDelChoque() {

        return postcondicion("notificó a la PiezaMovil que chocó contra un Contenedor", () -> {
            
            verify(PIEZA_MOVIL).chocoContraUnContenedor();
        });
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnContenedorDeAntimateria());

        comprobarQue(losPuntosInicialesDeUnContenedorDeAntimateriaSonCorrectos());
    }

    private Precondicion fueCreadoUnContenedorDeAntimateria() {

        return precondicion("fue creado unContenedorDeAntimateria", () -> {

            unContenedorDeAntimateria = new ContenedorDeAntimateria();
        });
    }

    private Postcondicion losPuntosInicialesDeUnContenedorDeAntimateriaSonCorrectos() {

        return postcondicion("los puntos iniciales de unContenedorDeAntimateria son correctos", () -> {

            assertThat(unContenedorDeAntimateria.obtenerPuntos()).as("puntos")
                    .isEqualTo(50);
        });
    }

}
