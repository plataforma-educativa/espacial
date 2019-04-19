package espacial.test;

import org.assertj.core.api.AbstractAssert;

import espacial.EspectroEspacial;
import espacial.Tablero;

public class AsercionSobreTablero extends AbstractAssert<AsercionSobreTablero, Tablero> {

    private EspectroEspacial espectroEsperado;
    
    public AsercionSobreTablero(Tablero actual) {
        super(actual, AsercionSobreTablero.class);
    }

    public AsercionSobreTablero tieneBase() {

        espectroEsperado = EspectroEspacial.BASE;
        
        return this;
    }
    
    public AsercionSobreTablero tieneVacio() {
        
        espectroEsperado = EspectroEspacial.VACIO;

        return this;
    }
    
    public AsercionSobreTablero tieneContenedor() {

        espectroEsperado = EspectroEspacial.CONTENEDOR;
        
        return this;
    }
    
    public AsercionSobreTablero tieneAsteroide() {

        espectroEsperado = EspectroEspacial.ASTEROIDE;
        
        return this;
    }
    
    public AsercionSobreTablero en(int fila, int columna) {
        
        return comprobarEspectroEsperadoEn(fila, columna);
    }
    
    public AsercionSobreTablero entre(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        
        for (int fila = filaInicial; fila <= filaFinal; fila++) {
            for (int columna = columnaInicial; columna <= columnaFinal; columna++) {
                comprobarEspectroEsperadoEn(fila, columna);
            }
        }
        
        return this;
    }
    
    private AsercionSobreTablero comprobarEspectroEsperadoEn(int fila, int columna) {

        isNotNull();

        EspectroEspacial espectro = actual.obtener(fila, columna);

        if (!espectroEsperado.equals(espectro)) {
            failWithMessage("EspectroEspacial del Tablero en [%d, %d] se esperaba%n <%s> %n pero fue %n <%s>", 
                            fila, columna, espectroEsperado, espectro);
        }
        
        return this;
    }

}
