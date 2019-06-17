package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgujeroNegroTest extends TestDeContratoSobrePieza<AgujeroNegro> {

    private AgujeroNegro unAgujeroNegro;

    @Override
    AgujeroNegro piezaCreada() {

        return new AgujeroNegro();
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion(() -> verify(NAVE_ESPACIAL).chocoContraUnAgujeroNegro());
    }

    @Test
    void obtenerPuntos() {

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
    void fueAtacadoCon() {

        dadoQue(fueCreadoUnAgujeroNegro());

        unAgujeroNegro.fueAtacadoCon(UN_ATAQUE);

        comprobarQue(unAtaqueNoTuvoEfectoSobreUnAgujeroNegro());
    }

    private Postcondicion unAtaqueNoTuvoEfectoSobreUnAgujeroNegro() {

        return postcondicion(() -> verifyZeroInteractions(UN_ATAQUE));
    }

    @Test
    void recibirUnaCarga() {

        dadoQue(fueCreadoUnAgujeroNegro());

        comprobarQue(generaExcepcionPorqueNoPuedeRecibirUnaCarga(() ->

                unAgujeroNegro.recibir(SustanciaEspacial.ANTIMATERIA.por(20))
        ));
    }

    private Postcondicion generaExcepcionPorqueNoPuedeRecibirUnaCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeRecibirUnaCarga.class)
        );
    }

    @Test
    void extraerUnaCarga() {

        dadoQue(fueCreadoUnAgujeroNegro());

        comprobarQue(generaExcepcionPorqueNoPuedeEntregarUnaCarga(() ->

                unAgujeroNegro.entregar(SustanciaEspacial.ANTIMATERIA.por(100)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeEntregarUnaCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeEntregarUnaCarga.class)
        );
    }

}
