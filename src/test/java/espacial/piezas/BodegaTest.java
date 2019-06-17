package espacial.piezas;


import espacial.Cargamento;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.excepciones.ExcedeLaCargaDisponible;
import espacial.test.Ejecutable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BodegaTest implements TestDeContrato {

    private Bodega unaBodega;

    @Test
    void crear() {

        final int capacidad = 400;

        unaBodega = new Bodega(capacidad);

        comprobarQue(unaBodegaTieneCargaConLugar(0, capacidad));
        comprobarQue(unaBodegaTieneCargamentosVacios());
    }

    private Postcondicion unaBodegaTieneCargaConLugar(int cargaEsperada, int lugarEsperado) {

        return postcondicion(() -> {

            assertThat(unaBodega.contabilizarCarga()).as("carga").isEqualTo(cargaEsperada);
            assertThat(unaBodega.contabilizarLugar()).as("lugar").isEqualTo(lugarEsperado);

        });
    }

    private Postcondicion unaBodegaTieneCargamentosVacios() {

        return postcondicion(() -> {

            assertThat(unaBodega.ANTIMATERIA).as("cargamento de ANTIMATERIA").isInstanceOf(Cargamento.class);
            assertThat(unaBodega.ANTIMATERIA.contar()).as("ANTIMATERIA.contar()").isEqualTo(0);
            assertThat(unaBodega.METAL).as("cargamento de METAL").isInstanceOf(Cargamento.class);
            assertThat(unaBodega.METAL.contar()).as("METAL.contar()").isEqualTo(0);
            assertThat(unaBodega.CRISTAL).as("cargamento de CRISTAL").isInstanceOf(Cargamento.class);
            assertThat(unaBodega.CRISTAL.contar()).as("CRISTAL.contar()").isEqualTo(0);
        });
    }

    @Test
    void agregarAlCargamentoDeANTIMATERIAOcupandoTodoElLugar() {

        final int capacidad = 100;

        dadoQue(fueCreadaLaBodegaConCapacidad(capacidad));

        unaBodega.ANTIMATERIA.agregar(capacidad);

        comprobarQue(elCargamentoDeANTIMATERIA(capacidad));
    }

    private Precondicion fueCreadaLaBodegaConCapacidad(int capacidad) {

        return precondicion(() -> unaBodega = new Bodega(capacidad));
    }

    private Postcondicion elCargamentoDeANTIMATERIA(int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unaBodega.ANTIMATERIA.contar())
                        .as("ANTIMATERIA.contar()")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    void agregarAlCargamentoDeMETALOcupandoTodoElLugar() {

        final int capacidad = 500;

        dadoQue(fueCreadaLaBodegaConCapacidad(capacidad));

        unaBodega.METAL.agregar(capacidad);

        comprobarQue(elCargamentoDeMETAL(capacidad));
    }

    private Postcondicion elCargamentoDeMETAL(int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unaBodega.METAL.contar())
                        .as("METAL.contar()")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    void agregarAlCargamentoDeCRISTALOcupandoTodoElLugar() {

        final int capacidad = 500;

        dadoQue(fueCreadaLaBodegaConCapacidad(capacidad));

        unaBodega.CRISTAL.agregar(capacidad);

        comprobarQue(elCargamentoDeCRISTAL(capacidad));
    }

    private Postcondicion elCargamentoDeCRISTAL(int cantidadEsperada) {

        return postcondicion(() ->

                assertThat(unaBodega.CRISTAL.contar())
                        .as("CRISTAL.contar()")
                        .isEqualTo(cantidadEsperada)
        );
    }

    @Test
    void agregarAlCargamentoDeANTIMATERIAMultiplesCargas() {

        final int primeraCarga = 40;
        final int segundaCarga = 20;

        dadoQue(fueCreadaLaBodegaConCapacidad(100));

        unaBodega.ANTIMATERIA.agregar(primeraCarga);
        unaBodega.ANTIMATERIA.agregar(segundaCarga);

        comprobarQue(elCargamentoDeANTIMATERIA(primeraCarga + segundaCarga));
    }

    @Test
    void agregarAlCargamentoDeANTIMATERIA51Teniendo50DeMETALConCapacidadDe100() {

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoMETAL(50));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBodega.ANTIMATERIA.agregar(51)
        ));
    }

    @Test
    void agregarAlCargamentoDeCRISTAL21Teniendo50DeANTIMATERIAConCapacidadDe70() {

        dadoQue(fueCreadaLaBodegaConCapacidad(70));
        dadoQue(unaBodegaCargoANTIMATERIA(50));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBodega.CRISTAL.agregar(21)
        ));
    }

    @Test
    void agregarAlCargamentoDeMETAL31Teniendo90DeCRISTALConCapacidadDe120() {

        dadoQue(fueCreadaLaBodegaConCapacidad(120));
        dadoQue(unaBodegaCargoCRISTAL(90));

        comprobarQue(generaExcepcionPorqueExcedeElLugarDisponible(() ->

                unaBodega.METAL.agregar(31)
        ));
    }

    @Test
    void retirarDelCargamentoDeCRISTAL() {

        final int cargaInicial = 90;
        final int cargaRetirada = 50;

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoCRISTAL(cargaInicial));

        unaBodega.CRISTAL.retirar(cargaRetirada);

        comprobarQue(elCargamentoDeCRISTAL(cargaInicial - cargaRetirada));
    }

    private Precondicion unaBodegaCargoANTIMATERIA(int cantidad) {

        return precondicion(() -> unaBodega.ANTIMATERIA.agregar(cantidad));
    }

    private Precondicion unaBodegaCargoMETAL(int cantidad) {

        return precondicion(() -> unaBodega.METAL.agregar(cantidad));
    }

    private Precondicion unaBodegaCargoCRISTAL(int cantidad) {

        return precondicion(() -> unaBodega.CRISTAL.agregar(cantidad));
    }

    @Test
    void retirarDelCargamentoDeMETALMasDeLoDisponible() {

        final int cargaInicial = 80;
        final int cargaRetirada = 90;

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoMETAL(cargaInicial));

        comprobarQue(generaExcepcionPorqueExcedeLaCargaDisponible(() -> unaBodega.METAL.retirar(cargaRetirada)));
    }

    private Postcondicion generaExcepcionPorqueExcedeLaCargaDisponible(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepcion generada")
                        .isInstanceOf(ExcedeLaCargaDisponible.class)
        );
    }

    private Postcondicion generaExcepcionPorqueExcedeElLugarDisponible(Ejecutable ejecutable) {

        return postcondicion(() ->

                assertThatThrownBy(ejecutable::ejecutar)
                        .as("excepción generada")
                        .isInstanceOf(ExcedeElLugarDisponible.class)
        );
    }

}