package espacial.test;

import espacial.Coordenada;
import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;
import espacial.Tablero;
import org.assertj.core.api.AbstractAssert;

import java.util.HashSet;
import java.util.Set;

/**
 * Aserción que permite comprobar el estado de un Tablero.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class AsercionSobreTablero extends AbstractAssert<AsercionSobreTablero, Tablero> {

    private EspectroEspacial espectroEsperado;

    private SustanciaEspacial sustanciaEsperada;

    private Set<Coordenada> coordenadasAsertadas = new HashSet<>();
    
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

    public AsercionSobreTablero tieneAgujeroNegro() {
        
        espectroEsperado = EspectroEspacial.DESCONOCIDO;
        
        return this;
    }
    
    public void yTieneVacioEnElResto() {

        tieneVacio();
        
        actual.conCadaCoordenada(this::comprobarEspectroEsperadoSiNoFueAsertada);
    }
    
    private void comprobarEspectroEsperadoSiNoFueAsertada(int fila, int columna) {
        
        if (! coordenadasAsertadas.contains(Coordenada.con(fila, columna))) {
            
            comprobarEspectroEsperadoEn(fila, columna);
        }
    }
    
    public AsercionSobreTablero en(int fila, int columna) {
        
        registrarCoordenadaAsertada(fila, columna);
        
        return comprobarEspectroEsperadoEn(fila, columna);
    }

    private void registrarCoordenadaAsertada(int fila, int columna) {

        coordenadasAsertadas.add(Coordenada.con(fila, columna));
    }
    
    public AsercionSobreTablero entre(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        
        for (int fila = filaInicial; fila <= filaFinal; fila++) {
            for (int columna = columnaInicial; columna <= columnaFinal; columna++) {
                
                registrarCoordenadaAsertada(fila, columna);
                comprobarEspectroEsperadoEn(fila, columna);
            }
        }
        
        return this;
    }
    
    private AsercionSobreTablero comprobarEspectroEsperadoEn(int fila, int columna) {

        isNotNull();

        EspectroEspacial espectro = actual.escanearEn(fila, columna);

        if (!espectroEsperado.equals(espectro)) {

            failWithMessage("EspectroEspacial del Tablero en [%d, %d] se esperaba%n <%s> %n pero fue %n <%s>",
                            fila, columna, espectroEsperado, espectro);
        }

        if ((sustanciaEsperada != null) && (actual.obtenerCasilleroEn(fila, columna).buscar(sustanciaEsperada) < 1)){

            failWithMessage("SustanciaEspacial del Tablero en [%d, %d] se esperaba encontrar%n <%s>",
                    fila, columna, sustanciaEsperada);
        }
        
        return this;
    }
}
