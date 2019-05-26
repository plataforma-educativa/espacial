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
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnContenedor());
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnContenedorDeAntimateria());

        comprobarQue(losPuntosInicialesDeUnContenedorDeAntimateriaSonCorrectos());
    }

    private Precondicion fueCreadoUnContenedorDeAntimateria() {

        return precondicion(() -> unContenedorDeAntimateria = new ContenedorDeAntimateria());
    }

    private Postcondicion losPuntosInicialesDeUnContenedorDeAntimateriaSonCorrectos() {

        return postcondicion(() -> assertThat(unContenedorDeAntimateria.obtenerPuntos()).as("puntos").isEqualTo(50));
    }

}
