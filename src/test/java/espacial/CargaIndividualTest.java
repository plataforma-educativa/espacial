package espacial;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CargaIndividualTest implements TestDeContrato {

    private final Deposito UN_DEPOSITO = mock(Deposito.class, "UN_DEPOSITO");

    private CargaIndividual unaCarga;

    @Test
    void obtenerCantidad() {

        final int cantidad = 3;
        dadoQue(fueCreadaUnaCargaIndividualCon(cantidad, SustanciaEspacial.ANTIMATERIA));

        comprobarQue(alObtenerCantidadDevuelve(cantidad));
    }

    private Precondicion fueCreadaUnaCargaIndividualCon(int cantidad, SustanciaEspacial sustancia) {

        return pre(() -> unaCarga = new CargaIndividual(cantidad, sustancia));
    }

    private Postcondicion alObtenerCantidadDevuelve(int cantidad) {

        return post(() ->

                assertThat(unaCarga.obtenerCantidad())
                        .as("cantidad")
                        .isEqualTo(cantidad)
        );
    }

    @Test
    void subirEn() {

        final int cantidad = 4;

        dadoQue(fueCreadaUnaCargaIndividualCon(cantidad, SustanciaEspacial.METAL));

        unaCarga.subirEn(UN_DEPOSITO);

        comprobarQue(enUnDepositoSeCargoMetalPor(cantidad));
    }

    private Postcondicion enUnDepositoSeCargoMetalPor(int cantidad) {

        return post(() -> verify(UN_DEPOSITO).cargarMetal(cantidad));
    }

    @Test
    void bajarDe() {

        final int cantidad = 53;

        dadoQue(fueCreadaUnaCargaIndividualCon(cantidad, SustanciaEspacial.CRISTAL));

        unaCarga.bajarDe(UN_DEPOSITO);

        comprobarQue(enUnDepositoSeDescargoCristalPor(cantidad));
    }

    private Postcondicion enUnDepositoSeDescargoCristalPor(int cantidad) {

        return post(() -> verify(UN_DEPOSITO).descargarCristal(cantidad));
    }

    @Test
    void tieneToString() {

        final int cantidad = 32;
        final SustanciaEspacial sustancia = SustanciaEspacial.METAL;
        dadoQue(fueCreadaUnaCargaIndividualCon(cantidad, sustancia));

        comprobarQue(unaCargaTieneDescripcion(cantidad, sustancia));
    }

    private Postcondicion unaCargaTieneDescripcion(int cantidad, SustanciaEspacial sustancia) {

        return post(() -> assertThat(unaCarga).hasToString(cantidad + " " + sustancia));
    }

    @Test
    void sonIgualesSiEsElMismoObjeto() {

        CargaIndividual carga = new CargaIndividual(6, SustanciaEspacial.METAL);

        assertThat(carga.equals(carga)).as("carga.equals(carga)").isTrue();
    }

    @Test
    void sonIgualesSiTienenLaMismaCantidadSustancia() {

        final int cantidad = 62;
        final SustanciaEspacial sustancia = SustanciaEspacial.CRISTAL;

        CargaIndividual primeraCarga = new CargaIndividual(cantidad, sustancia);
        CargaIndividual segundaCarga = new CargaIndividual(cantidad, sustancia);

        assertThat(primeraCarga.equals(segundaCarga))
                .as("primeraCarga.equals(segundaCarga)")
                .isTrue();
    }

    @Test
    void noSonIgualeSiTieneLaMismaCantidadPeroDiferenteSustancia() {

        final int cantidad = 69;

        CargaIndividual primeraCarga = new CargaIndividual(cantidad, SustanciaEspacial.METAL);
        CargaIndividual segundaCarga = new CargaIndividual(cantidad, SustanciaEspacial.CRISTAL);

        assertThat(primeraCarga.equals(segundaCarga))
                .as("primeraCarga.equals(segundaCarga)")
                .isFalse();
    }

    @Test
    void noSonIgualeSiTieneLaMismaSustanciaPeroDiferenteCantidad() {

        final SustanciaEspacial sustancia = SustanciaEspacial.CRISTAL;

        CargaIndividual primeraCarga = new CargaIndividual(3, sustancia);
        CargaIndividual segundaCarga = new CargaIndividual(5, sustancia);

        assertThat(primeraCarga.equals(segundaCarga))
                .as("primeraCarga.equals(segundaCarga)")
                .isFalse();
    }

    @Test
    void siSonIgualesTieneElMismoHash() {

        final int cantidad = 62;
        final SustanciaEspacial sustancia = SustanciaEspacial.CRISTAL;

        CargaIndividual primeraCarga = new CargaIndividual(cantidad, sustancia);
        CargaIndividual segundaCarga = new CargaIndividual(cantidad, sustancia);

        assertThat(primeraCarga).hasSameHashCodeAs(segundaCarga);
    }
}