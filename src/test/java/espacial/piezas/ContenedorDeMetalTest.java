package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.excepciones.ExcedeLaCargaDisponible;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContenedorDeMetalTest extends TestDeContratoSobrePieza<ContenedorDeMetal> {

    private ContenedorDeMetal unContenedorDeMetal;

    @Override
    public ContenedorDeMetal piezaCreada() {

        return new ContenedorDeMetal();
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

        dadoQue(fueCreadoUnContenedorDeMetal());

        comprobarQue(losPuntosInicialesDeUnContenedorDeMetalSonCorrectos());
    }

    private Precondicion fueCreadoUnContenedorDeMetal() {

        return precondicion(() -> unContenedorDeMetal = new ContenedorDeMetal());
    }

    private Postcondicion losPuntosInicialesDeUnContenedorDeMetalSonCorrectos() {

        return postcondicion(() -> assertThat(unContenedorDeMetal.obtenerPuntos()).as("puntos").isEqualTo(50));
    }

    @Test
    public void recibirUnaCarga() {

        final int cantidad = 65;

        dadoQue(fueCreadoUnContenedorDeMetal());

        unContenedorDeMetal.recibir(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unContenedorTiene(SustanciaEspacial.METAL, cantidad));
    }

    private Postcondicion unContenedorTiene(SustanciaEspacial sustanciaEsperada, int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unContenedorDeMetal.buscar(sustanciaEsperada))
                        .as("buscar(" + sustanciaEsperada + ")")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    public void recibirMultiplesCargas() {

        final int cantidadInicial = 20;
        final int cantidad = 90;

        dadoQue(fueCreadoUnContenedorDeMetalRecibiendo(cantidadInicial));

        unContenedorDeMetal.recibir(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unContenedorTiene(SustanciaEspacial.METAL, cantidadInicial + cantidad));
    }

    private Precondicion fueCreadoUnContenedorDeMetalRecibiendo(int cantidadInicial) {

        return precondicion(() -> {

            unContenedorDeMetal = new ContenedorDeMetal();
            unContenedorDeMetal.recibir(SustanciaEspacial.METAL.por(cantidadInicial));
        });
    }

    @Test
    public void recibirLaCargaMaxima() {

        final int cantidadMaxima = 1000;

        dadoQue(fueCreadoUnContenedorDeMetal());

        unContenedorDeMetal.recibir(SustanciaEspacial.METAL.por(cantidadMaxima));

        comprobarQue(unContenedorTiene(SustanciaEspacial.METAL, cantidadMaxima));
    }

    @Test
    public void recibirCargaExcediendoLaCapacidad() {

        final int cantidadExcedida = 1001;

        dadoQue(fueCreadoUnContenedorDeMetal());

        comprobarQue(generaExcepcionPorqueExcedeLaCapacidadDeCarga(() ->

                unContenedorDeMetal.recibir(SustanciaEspacial.METAL.por(cantidadExcedida)))
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeLaCapacidadDeCarga(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeLaCapacidadDeCarga.class)
        );
    }

    @Test
    public void extraerUnaCarga() {

        final int cantidadInicial = 90;
        final int cantidad = 10;

        dadoQue(fueCreadoUnContenedorDeMetalRecibiendo(cantidadInicial));

        unContenedorDeMetal.entregar(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unContenedorTiene(SustanciaEspacial.METAL, cantidadInicial - cantidad));
    }

    @Test
    public void extraerMultiplesCargas() {

        final int cantidadInicial = 500;
        final int cantidadExtraida = 343;
        final int cantidad = 132;

        dadoQue(fueCreadoUnContenedorDeMetalRecibiendo(cantidadInicial));
        dadoQue(fueExtraidaDeUnContenedorDeMetalUnaCarga(cantidadExtraida));

        unContenedorDeMetal.entregar(SustanciaEspacial.METAL.por(cantidad));

        comprobarQue(unContenedorTiene(SustanciaEspacial.METAL, cantidadInicial - cantidadExtraida - cantidad));
    }

    @Test
    public void extraerExcediendoLaCargaDisponible() {

        final int cantidadInicial = 100;
        final int cantidad = 101;

        dadoQue(fueCreadoUnContenedorDeMetalRecibiendo(cantidadInicial));

        comprobarQue(generaExcepcionPorqueExcedeLaCargaDisponible(() ->

                unContenedorDeMetal.entregar(SustanciaEspacial.METAL.por(cantidad)))
        );
    }

    @Test
    public void extraerTodaLaCargaDisponible() {

        final int cantidadInicial = 345;

        dadoQue(fueCreadoUnContenedorDeMetalRecibiendo(cantidadInicial));

        unContenedorDeMetal.entregar(SustanciaEspacial.METAL.por(cantidadInicial));

        comprobarQue(unContenedorTiene(SustanciaEspacial.METAL, 0));
    }

    private Precondicion fueExtraidaDeUnContenedorDeMetalUnaCarga(int cantidadExtraida) {

        return precondicion(() ->

                unContenedorDeMetal.entregar(SustanciaEspacial.METAL.por(cantidadExtraida))
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeLaCargaDisponible(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeLaCargaDisponible.class)
        );
    }
}
