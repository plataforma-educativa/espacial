package espacial;

import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.excepciones.ExcedeLaCargaDisponible;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CargamentoTest implements TestDeContrato {

    private Cargamento unCargamento;

    @Test
    public void agregar40CuandoTiene0ConCapacidadPara100() {

        final int capacidad = 100;
        final int agregada = 40;
        dadoQue(fueCreadoUnCargamento(capacidad));

        unCargamento.agregar(agregada);

        comprobarQue(contarDevuelve(agregada));
    }

    @Test
    public void agregar50CuandoTiene10ConCapacidadPara200() {

        final int capacidad = 200;
        final int inicial = 10;
        final int agregada = 50;
        dadoQue(fueCreadoUnCargamento(capacidad, inicial));

        unCargamento.agregar(agregada);

        comprobarQue(contarDevuelve(inicial + agregada));
    }

    @Test
    public void agregar70CuandoTiene20ConCapacidadPara90() {

        final int capacidad = 90;
        final int inicial = 20;
        final int agregada = 70;
        dadoQue(fueCreadoUnCargamento(capacidad, inicial));

        unCargamento.agregar(agregada);

        comprobarQue(contarDevuelve(inicial + agregada));
    }

    @Test
    public void agregar90CuandoTiene100ConCapacidadPara189() {

        final int capacidad = 189;
        final int inicial = 100;
        final int agregada = 90;
        dadoQue(fueCreadoUnCargamento(capacidad, inicial));

        comprobarQue(generaExcepcionPorqueExcedeLaCapacidadDeCarga(() -> unCargamento.agregar(agregada),
                inicial + agregada, capacidad));
    }

    @Test
    public void retirar10CuandoTiene25() {

        final int capacidad = 50;
        final int disponible = 30;
        final int retirada = 25;
        dadoQue(fueCreadoUnCargamento(capacidad, disponible));

        unCargamento.retirar(retirada);

        comprobarQue(contarDevuelve(disponible - retirada));
    }

    @Test
    public void retirar40CuandoTiene40() {

        final int capacidad = 50;
        final int disponible = 30;
        dadoQue(fueCreadoUnCargamento(capacidad, disponible));

        unCargamento.retirar(disponible);

        comprobarQue(contarDevuelve(0));
    }

    @Test
    public void retirar60CuandoTiene30() {

        final int capacidad = 70;
        final int disponible = 30;
        final int retirada = 60;
        dadoQue(fueCreadoUnCargamento(capacidad, disponible));

        comprobarQue(generaExcepcionPorqueExcedeLaCargaDisponible(() -> unCargamento.retirar(retirada),
                retirada, disponible));
    }

    private Precondicion fueCreadoUnCargamento(int capacidad) {

        return precondicion(() -> unCargamento = new Cargamento(capacidad));
    }

    private Precondicion fueCreadoUnCargamento(int capacidad, int cantidad) {

        return precondicion(() -> {

            unCargamento = new Cargamento(capacidad);
            unCargamento.agregar(cantidad);
        });
    }

    private Postcondicion contarDevuelve(int cantidadEsperada) {

        return postcondicion(() -> assertThat(unCargamento.contar()).as("contar()").isEqualTo(cantidadEsperada));
    }

    private Postcondicion generaExcepcionPorqueExcedeLaCargaDisponible(Ejecutable ejecutable,
                                                                       int retirada, int disponible) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeLaCargaDisponible.class)
                        .hasMessage("'%d' excede la carga disponible de '%d'", retirada, disponible)
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeLaCapacidadDeCarga(Ejecutable ejecutable,
                                                                        int total, int capacidad) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeLaCapacidadDeCarga.class)
                        .hasMessage("'%d' excede la capacidad de carga de '%d'", total, capacidad)
        );
    }
}