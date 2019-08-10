package espacial.piezas;

import espacial.Carga;
import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.ExcedeElLugarDisponible;
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
    Postcondicion evaluoLaCondicionDePartidarioEsperada() {

        return evaluoQueEsNeutral();
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

    @Test
    void recibirUnaCargaDeAntimateria() {

        final int cantidad = 34;

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.ANTIMATERIA, cantidad));
    }

    private Postcondicion unaBaseDesconocidaTiene(SustanciaEspacial sustanciaEsperada, int cantidadEsperada) {

        return post(condicion ->

                assertThat(unaBaseDesconocida.buscar(sustanciaEsperada))
                        .as("buscar(" + sustanciaEsperada + ")")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    void recibirMultiplesCargasDeAntimateria() {

        final int cantidadInicial = 45;
        final int cantidad = 20;

        dadoQue(fueCreadaUnaBaseDesconocidaRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBaseDesconocida.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidad));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial + cantidad));
    }

    private Precondicion fueCreadaUnaBaseDesconocidaRecibiendo(Carga unaCarga) {

        return pre(condicion ->  {

            unaBaseDesconocida = new BaseDesconocida();
            unaBaseDesconocida.recibir(unaCarga);
        });
    }

    @Test
    void recibirLaCargaMaximaDeAntimateria() {

        final int cantidadMaxima = 5000;

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadMaxima));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.ANTIMATERIA, cantidadMaxima));
    }

    @Test
    void recibirCargaExcediendoLaCapacidadDeAntimateria() {

        final int cantidadExcedida = 5001;

        dadoQue(fueCreadaUnaBaseDesconocida());

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBaseDesconocida.recibir(SustanciaEspacial.ANTIMATERIA.por(cantidadExcedida)))
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeElLugarDisponible(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeElLugarDisponible.class)
        );
    }

    @Test
    void entregarUnaCarga() {

        final int cantidadInicial = 400;
        final int cantidadEntregada = 150;

        dadoQue(fueCreadaUnaBaseDesconocidaRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBaseDesconocida.entregar(SustanciaEspacial.ANTIMATERIA.por(cantidadEntregada));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial - cantidadEntregada));
    }

    @Test
    void recibirUnaCargaDeMetal() {

        final int cantidad = 400;

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.recibir(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.METAL, cantidad));
    }

    @Test
    void recibirUnaCargaDeCristal() {

        final int cantidad = 320;

        dadoQue(fueCreadaUnaBaseDesconocida());

        unaBaseDesconocida.recibir(SustanciaEspacial.CRISTAL.por(cantidad));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.CRISTAL, cantidad));
    }

    @Test
    void entregarUnaCargaDeAntimateria() {

        final int cantidadInicial = 60;
        final int cantidadRetirada = 12;

        dadoQue(fueCreadaUnaBaseDesconocidaRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicial)));

        unaBaseDesconocida.entregar(SustanciaEspacial.ANTIMATERIA.por(cantidadRetirada));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.ANTIMATERIA, cantidadInicial - cantidadRetirada));
    }

    @Test
    void entregarUnaCargaDeCristal() {

        final int cantidadInicial = 500;
        final int cantidadRetirada = 120;

        dadoQue(fueCreadaUnaBaseDesconocidaRecibiendo(SustanciaEspacial.CRISTAL.por(cantidadInicial)));

        unaBaseDesconocida.entregar(SustanciaEspacial.CRISTAL.por(cantidadRetirada));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.CRISTAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    void entregarUnaCargaDeMETAL() {

        final int cantidadInicial = 1300;
        final int cantidadRetirada = 450;

        dadoQue(fueCreadaUnaBaseDesconocidaRecibiendo(SustanciaEspacial.METAL.por(cantidadInicial)));

        unaBaseDesconocida.entregar(SustanciaEspacial.METAL.por(cantidadRetirada));

        comprobarQue(unaBaseDesconocidaTiene(SustanciaEspacial.METAL, cantidadInicial - cantidadRetirada));
    }

    @Test
    void recibirCargaDeCristalQueExcedeLaCapacidadPorqueTieneAntimateria() {

        final int cantidadInicialDeAntimateria = 3000;
        final int cantidadDeCristal = 2001;

        dadoQue(fueCreadaUnaBaseDesconocidaRecibiendo(SustanciaEspacial.ANTIMATERIA.por(cantidadInicialDeAntimateria)));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBaseDesconocida.recibir(SustanciaEspacial.METAL.por(cantidadDeCristal)))
        );
    }

}
