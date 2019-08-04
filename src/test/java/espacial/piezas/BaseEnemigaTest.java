package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.NoPuedeEntregarUnaCarga;
import espacial.excepciones.NoPuedeRecibirUnaCarga;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseEnemigaTest extends TestDeContratoSobrePieza<BaseDesconocida> {

    private BaseDesconocida unaBaseEnemiga;

    @Override
    BaseDesconocida piezaCreada() {

        return new BaseDesconocida();
    }

    @Override
    EspectroEspacial espectroEsperado() {

        return EspectroEspacial.BASE;
    }

    @Override
    Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return post(condicion ->  verify(NAVE_ESPACIAL).chocoContraUnaBase());
    }

    @Test
    void obtenerPuntos() {

        dadoQue(fueCreadaUnaBaseEnemiga());

        comprobarQue(losPuntosInicialesDeUnaBaseSonCorrectos());
    }

    private Precondicion fueCreadaUnaBaseEnemiga() {

        return pre(condicion ->  unaBaseEnemiga = new BaseDesconocida());
    }

    private Postcondicion losPuntosInicialesDeUnaBaseSonCorrectos() {

        return post(condicion ->  assertThat(unaBaseEnemiga.obtenerPuntos()).as("puntos").isEqualTo(200));
    }

    @Test
    void recibirUnaCarga() {

        dadoQue(fueCreadaUnaBaseEnemiga());

        comprobarQue(generaExcepcionPorqueNoPuedeRecirCarga(() ->

                unaBaseEnemiga.recibir(SustanciaEspacial.ANTIMATERIA.por(34)))
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

        dadoQue(fueCreadaUnaBaseEnemiga());

        comprobarQue(generaExcepcionPorqueNoPuedeEntregarUnaCarga(() ->

                unaBaseEnemiga.entregar(SustanciaEspacial.ANTIMATERIA.por(100)))
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

        dadoQue(fueCreadaUnaBaseEnemiga());

        unaBaseEnemiga.atacadoConLaser();

        comprobarQue(losPuntosDeUnaBaseEnemigaSon(195));
    }
    private Postcondicion losPuntosDeUnaBaseEnemigaSon(int esperados) {

        return post(condicion ->  assertThat(unaBaseEnemiga.obtenerPuntos()).as("puntos").isEqualTo(esperados));
    }

    @Test
    void atacadoConUnTopedoDeFotones() {

        dadoQue(fueCreadaUnaBaseEnemiga());

        unaBaseEnemiga.atacadoConTorpedoDeFotones();

        comprobarQue(losPuntosDeUnaBaseEnemigaSon(190));
    }

}
