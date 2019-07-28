package espacial.piezas;


import espacial.Cargamento;
import espacial.excepciones.ExcedeElLugarDisponible;
import espacial.excepciones.ExcedeLaCargaDisponible;
import espacial.test.Operacion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import espacial.utiles.Accion;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BodegaTest implements TestDeContrato {

    private final Accion UNA_ACCION = mock(Accion.class, "UNA_ACCION");

    private Bodega unaBodega;

    @Test
    void crear() {

        final int capacidad = 400;

        unaBodega = new Bodega(capacidad);

        comprobarQue(unaBodegaTieneCargaConLugar(0, capacidad));
        comprobarQue(unaBodegaTieneCargamentosVacios());
    }

    private Postcondicion unaBodegaTieneCargaConLugar(int cargaEsperada, int lugarEsperado) {

        return post(condicion ->  {

            assertThat(unaBodega.contabilizarCarga()).as("carga").isEqualTo(cargaEsperada);
            assertThat(unaBodega.contabilizarLugar()).as("lugar").isEqualTo(lugarEsperado);

        });
    }

    private Postcondicion unaBodegaTieneCargamentosVacios() {

        return post(condicion ->  {

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

        return pre(condicion ->  unaBodega = new Bodega(capacidad));
    }

    private Postcondicion elCargamentoDeANTIMATERIA(int cantidadEsperada) {

        return post(condicion ->

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

        return post(condicion ->

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

        return post(condicion ->

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

        return pre(condicion ->  unaBodega.ANTIMATERIA.agregar(cantidad));
    }

    private Precondicion unaBodegaCargoMETAL(int cantidad) {

        return pre(condicion ->  unaBodega.METAL.agregar(cantidad));
    }

    private Precondicion unaBodegaCargoCRISTAL(int cantidad) {

        return pre(condicion ->  unaBodega.CRISTAL.agregar(cantidad));
    }

    @Test
    void retirarDelCargamentoDeMETALMasDeLoDisponible() {

        final int cargaInicial = 80;
        final int cargaRetirada = 90;

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoMETAL(cargaInicial));

        comprobarQue(generaExcepcionPorqueExcedeLaCargaDisponible(() ->  unaBodega.METAL.retirar(cargaRetirada)));
    }

    private Postcondicion generaExcepcionPorqueExcedeLaCargaDisponible(Operacion operacion) {

        return post(condicion ->

                assertThatThrownBy(operacion::ejecutar)
                        .as("excepcion generada")
                        .isInstanceOf(ExcedeLaCargaDisponible.class)
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
    void contabilizarNivelDeCarga() {

        dadoQue(fueCreadaLaBodegaConCapacidad(500));
        dadoQue(unaBodegaCargoCRISTAL(100));
        dadoQue(unaBodegaCargoANTIMATERIA(100));
        dadoQue(unaBodegaCargoMETAL(50));

        comprobarQue(elNivelDeCargaEs(50));
    }

    private Postcondicion elNivelDeCargaEs(int nivel) {

        return post(condicion ->  assertThat(unaBodega.obtenerNivelDeCarga()).as("nivel de carga").isEqualTo(nivel));
    }

    @Test
    void cuandoCambiaLaCargaPorqueSeRetiroAntimateria() {

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoANTIMATERIA(10));

        unaBodega.cuandoCambiaLaCarga(UNA_ACCION);
        unaBodega.ANTIMATERIA.retirar(5);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    private Postcondicion ejecutoUnaAccionConfigurada() {

        return post(condicion -> verify(UNA_ACCION).ejecutar());
    }

    @Test
    void cuandoCambiaLaCargaPorqueSeAgregoAntimateria() {

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoANTIMATERIA(10));

        unaBodega.cuandoCambiaLaCarga(UNA_ACCION);
        unaBodega.ANTIMATERIA.agregar(5);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    @Test
    void cuandoCambiaLaCargaPorqueSeAgregoMetal() {

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoMETAL(10));

        unaBodega.cuandoCambiaLaCarga(UNA_ACCION);
        unaBodega.METAL.agregar(5);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    @Test
    void cuandoCambiaLaCargaPorqueSeRetiroCristal() {

        dadoQue(fueCreadaLaBodegaConCapacidad(100));
        dadoQue(unaBodegaCargoCRISTAL(30));

        unaBodega.cuandoCambiaLaCarga(UNA_ACCION);
        unaBodega.CRISTAL.agregar(7);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }
}