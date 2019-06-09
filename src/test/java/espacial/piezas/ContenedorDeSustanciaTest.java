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

public abstract class ContenedorDeSustanciaTest<T extends ContenedorDeSustancia> extends TestDeContratoSobrePieza<T> {

    private T unContenedor;

    protected abstract SustanciaEspacial sustanciaAlmacenada();

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

        dadoQue(fueCreadoUnContenedor());

        comprobarQue(losPuntosInicialesDeUnContenedorDeSustanciaSonCorrectos());
    }

    private Precondicion fueCreadoUnContenedor() {

        return precondicion(() -> unContenedor = piezaCreada());
    }

    private Postcondicion losPuntosInicialesDeUnContenedorDeSustanciaSonCorrectos() {

        return postcondicion(() -> assertThat(unContenedor.obtenerPuntos()).as("puntos").isEqualTo(50));
    }

    @Test
    public void recibirUnaCarga() {

        final int cantidad = 65;

        dadoQue(fueCreadoUnContenedor());

        unContenedor.recibir(sustanciaAlmacenada().por(cantidad));

        comprobarQue(unContenedorTiene(sustanciaAlmacenada(), cantidad));
    }

    private Postcondicion unContenedorTiene(SustanciaEspacial sustanciaEsperada, int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unContenedor.buscar(sustanciaEsperada))
                        .as("buscar(" + sustanciaEsperada + ")")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    public void recibirMultiplesCargas() {

        final int cantidadInicial = 20;
        final int cantidad = 90;

        dadoQue(fueCreadoUnContenedorDeSustanciaRecibiendo(cantidadInicial));

        unContenedor.recibir(sustanciaAlmacenada().por(cantidad));

        comprobarQue(unContenedorTiene(sustanciaAlmacenada(), cantidadInicial + cantidad));
    }

    private Precondicion fueCreadoUnContenedorDeSustanciaRecibiendo(int cantidadInicial) {

        return precondicion(() -> {

            unContenedor = piezaCreada();
            unContenedor.recibir(sustanciaAlmacenada().por(cantidadInicial));
        });
    }

    @Test
    public void recibirLaCargaMaxima() {

        final int cantidadMaxima = 1000;

        dadoQue(fueCreadoUnContenedor());

        unContenedor.recibir(sustanciaAlmacenada().por(cantidadMaxima));

        comprobarQue(unContenedorTiene(sustanciaAlmacenada(), cantidadMaxima));
    }

    @Test
    public void recibirCargaExcediendoLaCapacidad() {

        final int cantidadExcedida = 1001;

        dadoQue(fueCreadoUnContenedor());

        comprobarQue(generaExcepcionPorqueExcedeLaCapacidadDeCarga(() ->

                unContenedor.recibir(sustanciaAlmacenada().por(cantidadExcedida)))
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

        dadoQue(fueCreadoUnContenedorDeSustanciaRecibiendo(cantidadInicial));

        unContenedor.entregar(sustanciaAlmacenada().por(cantidad));

        comprobarQue(unContenedorTiene(sustanciaAlmacenada(), cantidadInicial - cantidad));
    }

    @Test
    public void extraerMultiplesCargas() {

        final int cantidadInicial = 500;
        final int cantidadExtraida = 343;
        final int cantidad = 132;

        dadoQue(fueCreadoUnContenedorDeSustanciaRecibiendo(cantidadInicial));
        dadoQue(fueExtraidaDeUnContenedorDeSustanciaUnaCarga(cantidadExtraida));

        unContenedor.entregar(sustanciaAlmacenada().por(cantidad));

        comprobarQue(unContenedorTiene(sustanciaAlmacenada(), cantidadInicial - cantidadExtraida - cantidad));
    }

    @Test
    public void extraerExcediendoLaCargaDisponible() {

        final int cantidadInicial = 100;
        final int cantidad = 101;

        dadoQue(fueCreadoUnContenedorDeSustanciaRecibiendo(cantidadInicial));

        comprobarQue(generaExcepcionPorqueExcedeLaCargaDisponible(() ->

                unContenedor.entregar(sustanciaAlmacenada().por(cantidad)))
        );
    }

    @Test
    public void extraerTodaLaCargaDisponible() {

        final int cantidadInicial = 345;

        dadoQue(fueCreadoUnContenedorDeSustanciaRecibiendo(cantidadInicial));

        unContenedor.entregar(sustanciaAlmacenada().por(cantidadInicial));

        comprobarQue(unContenedorTiene(sustanciaAlmacenada(), 0));
    }

    private Precondicion fueExtraidaDeUnContenedorDeSustanciaUnaCarga(int cantidadExtraida) {

        return precondicion(() ->

                unContenedor.entregar(sustanciaAlmacenada().por(cantidadExtraida))
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
