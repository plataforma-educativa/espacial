package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Faccion;
import espacial.SustanciaEspacial;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseDesconocidaTest extends TestDeContratoSobrePieza<BaseDesconocida> {

    private BaseDesconocida unaBaseDesconocida;

    @Override
    BaseDesconocida piezaCreada() {

        return new BaseDesconocida();
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Override
    Faccion faccionEsperada() {

        return Faccion.NEUTRAL;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return post(condicion ->  verify(NAVE_ESPACIAL).chocoContraUnaBase());
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadaUnaBaseDesconocida());

        comprobarQue(losPuntosInicialesDeUnaBaseSonCorrectos());
    }

    private Precondicion fueCreadaUnaBaseDesconocida() {

        return pre(condicion ->  unaBaseDesconocida = new BaseDesconocida());
    }

    private Postcondicion losPuntosInicialesDeUnaBaseSonCorrectos() {

        return post(condicion ->  assertThat(unaBaseDesconocida.obtenerPuntos()).as("puntos").isEqualTo(200));
    }

    @Test
    void recibirUnaCarga() {

        dadoQue(fueCreadaUnaBaseDesconocida());

        comprobarQue(generaExcepcionPorqueNoPuedeRecirCarga(() ->

                unaBaseDesconocida.recibir(SustanciaEspacial.ANTIMATERIA.por(34)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeRecirCarga(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción lanzada")
                        .isInstanceOf(NoPuedeRecibirUnaCarga.class)
        );
    }

    @Test
    void extraerUnaCarga() {

        dadoQue(fueCreadaUnaBaseDesconocida());

        comprobarQue(generaExcepcionPorqueNoPuedeEntregarUnaCarga(() ->

                unaBaseDesconocida.entregar(SustanciaEspacial.ANTIMATERIA.por(100)))
        );
    }

    private Postcondicion generaExcepcionPorqueNoPuedeEntregarUnaCarga(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(NoPuedeEntregarUnaCarga.class)
        );
    }

    @Test
    void atacadoConLaser() {

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.atacadoConLaser();

        comprobarQue(losPuntosDeUnaBaseDesconocidaSon(195));
    }
    private Postcondicion losPuntosDeUnaBaseDesconocidaSon(int esperados) {

        return post(condicion ->  assertThat(unaBaseDesconocida.obtenerPuntos()).as("puntos").isEqualTo(esperados));
    }

    @Test
    void atacadoConUnTopedoDeFotones() {

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.atacadoConTorpedoDeFotones();

        comprobarQue(losPuntosDeUnaBaseDesconocidaSon(190));
    }

    @Test
    void aceptar() {

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.aceptar(UN_VISITANTE);

        comprobarQue(unVisitanteEsBase());
    }

    private Postcondicion unVisitanteEsBase() {

        return post(condicion -> verify(UN_VISITANTE).siEsBase(unaBaseDesconocida));
    }
}
