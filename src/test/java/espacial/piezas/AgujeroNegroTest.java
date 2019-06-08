package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgujeroNegroTest extends TestDeContratoSobrePieza<AgujeroNegro> {

    private AgujeroNegro unAgujeroNegro;

    @Override
    public AgujeroNegro piezaCreada() {

        return new AgujeroNegro();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnAgujeroNegro());
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnAgujeroNegro());

        comprobarQue(losPuntosUnAgujeroNegroSonMaximos());

    }

    private Precondicion fueCreadoUnAgujeroNegro() {

        return precondicion(() -> unAgujeroNegro = new AgujeroNegro());
    }

    private Postcondicion losPuntosUnAgujeroNegroSonMaximos() {

        return postcondicion(() ->
                assertThat(unAgujeroNegro.obtenerPuntos())
                        .as("puntos")
                        .isEqualTo(Pieza.PUNTOS_MAXIMOS));
    }

    @Test
    public void fueAtacadoCon() {

        dadoQue(fueCreadoUnAgujeroNegro());

        unAgujeroNegro.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(unAtaqueNoTuvoEfectoSobreUnAgujeroNegro());
    }

    private Postcondicion unAtaqueNoTuvoEfectoSobreUnAgujeroNegro() {

        return postcondicion(() -> verifyZeroInteractions(UN_ATAQUE));
    }

    @Test
    public void recibirUnaCarga() {

        dadoQue(fueCreadoUnAgujeroNegro());

        unAgujeroNegro.recibir(SustanciaEspacial.ANTIMATERIA.por(20));

        comprobarQue(noGeneraExcepcionPeroTampocoCambiaElEstadoDeLaPieza());
    }

    private Postcondicion noGeneraExcepcionPeroTampocoCambiaElEstadoDeLaPieza() {

        return postcondicion(() -> {});
    }
}
