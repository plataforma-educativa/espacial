package espacial.test;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.AbstractAssert;

import espacial.Coordenada;
import espacial.EspectroEspacial;
import espacial.Tablero;

/**
 * Aserción que permite comprobar el estado de un Tablero.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class AsercionSobreTablero extends AbstractAssert<AsercionSobreTablero, Tablero> {

    private EspectroEspacial espectroEsperado;
    
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
        
        return this;
    }


    public AsercionSobreTablero tieneAgujeroNegro() {
        
        espectroEsperado = EspectroEspacial.DESCONOCIDO;
        
        return this;
    }
    
    public void yTieneVacioEnElResto() {

        tieneVacio();
        
        actual.conCadaCoordenada((fila, columna) -> comprobarEspectroEsperadoSiNoFueAsertada(fila, columna));
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
        
        return this;
    }
}
