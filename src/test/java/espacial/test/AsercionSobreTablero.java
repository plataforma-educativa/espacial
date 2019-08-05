package espacial.test;

import espacial.Casillero;
import espacial.Coordenadas;
import espacial.EspectroEspacial;
import espacial.Faccion;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import org.assertj.core.api.AbstractAssert;

import java.util.HashSet;
import java.util.Set;

/**
 * Aserción que permite comprobar el estado de un Tablero.
 *
 * @author Mariano Tugnarelli
 */
public class AsercionSobreTablero extends AbstractAssert<AsercionSobreTablero, Tablero> {

    private EspectroEspacial espectroEsperado;

    private SustanciaEspacial sustanciaEsperada;

    private Faccion faccionEsperada;

    private Set<Coordenadas> coordenadasAsertadas = new HashSet<>();

    public AsercionSobreTablero(Tablero actual) {
        super(actual, AsercionSobreTablero.class);
    }

    public AsercionSobreTablero tieneBase() {

        return tiene(EspectroEspacial.BASE);
    }

    public AsercionSobreTablero tieneVacio() {

        return tiene(EspectroEspacial.VACIO);
    }

    public AsercionSobreTablero tieneContenedor() {

        return tiene(EspectroEspacial.CONTENEDOR);
    }

    public AsercionSobreTablero tieneAsteroide() {

        return tiene(EspectroEspacial.ASTEROIDE);
    }

    public AsercionSobreTablero tieneNave() {

        return tiene(EspectroEspacial.NAVE);
    }

    private AsercionSobreTablero tiene(EspectroEspacial espectro) {

        espectroEsperado = espectro;
        sustanciaEsperada = null;

        return this;
    }

    public AsercionSobreTablero conAntimateria() {

        return con(SustanciaEspacial.ANTIMATERIA);
    }

    public AsercionSobreTablero conMetal() {

        return con(SustanciaEspacial.METAL);
    }

    public AsercionSobreTablero conCristal() {

        return con(SustanciaEspacial.CRISTAL);
    }

    private AsercionSobreTablero con(SustanciaEspacial sustancia) {

        sustanciaEsperada = sustancia;

        return this;
    }

    public AsercionSobreTablero de(Faccion faccion) {

        faccionEsperada = faccion;

        return this;
    }

    public AsercionSobreTablero tieneAgujeroNegro() {

        espectroEsperado = EspectroEspacial.DESCONOCIDO;

        return this;
    }

    public void yTieneVacioEnElResto() {

        tieneVacio();

        actual.conCadaCoordenada(this::comprobarEspectroEsperadoSiNoFueAsertada);
    }

    private void comprobarEspectroEsperadoSiNoFueAsertada(int fila, int columna) {

        if (!coordenadasAsertadas.contains(Coordenadas.con(fila, columna))) {

            comprobarCasilleroEn(fila, columna);
        }
    }

    public AsercionSobreTablero en(int fila, int columna) {

        registrarCoordenadaAsertada(fila, columna);

        return comprobarCasilleroEn(fila, columna);
    }

    private void registrarCoordenadaAsertada(int fila, int columna) {

        coordenadasAsertadas.add(Coordenadas.con(fila, columna));
    }

    public AsercionSobreTablero entre(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {

        for (int fila = filaInicial; fila <= filaFinal; fila++) {
            for (int columna = columnaInicial; columna <= columnaFinal; columna++) {

                registrarCoordenadaAsertada(fila, columna);
                comprobarCasilleroEn(fila, columna);
            }
        }

        return this;
    }

    private AsercionSobreTablero comprobarCasilleroEn(int fila, int columna) {

        isNotNull();

        Casillero casillero = actual.obtenerCasilleroEn(fila, columna);

        comprobarEspectroEsperadoEn(casillero);
        comprobarSustanciaEsperadaEn(casillero);
        comprobarFaccionEsperadaEn(casillero);

        return this;
    }

    private void comprobarEspectroEsperadoEn(Casillero casillero) {

        if (espectroEsperado != casillero.escanear()) {

            failWithMessage("EspectroEspacial del Tablero en %s se esperaba%n <%s> %n pero fue %n <%s>",
                    casillero.obtenerCoordenadas(), espectroEsperado, casillero.escanear());
        }
    }

    private void comprobarSustanciaEsperadaEn(Casillero casillero) {

        if ((sustanciaEsperada != null) && (casillero.buscar(sustanciaEsperada) < 1)) {

            failWithMessage("SustanciaEspacial del Tablero en %s se esperaba encontrar%n <%s>",
                    casillero.obtenerCoordenadas(), sustanciaEsperada);
        }
    }

    private void comprobarFaccionEsperadaEn(Casillero casillero) {

        if ((faccionEsperada != null) && (faccionEsperada != casillero.reconocer())) {

            failWithMessage("Facción del Tablero en %s se esperaba encontrar%n <%s> %n pero fue %n <%s>",
                    casillero.obtenerCoordenadas(), faccionEsperada, casillero.reconocer());
        }
    }
}
