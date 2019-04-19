package espacial.test;

import espacial.Tablero;

public class Aserciones {

    public static AsercionSobreTablero assertThat(Tablero tablero) {
        
        return new AsercionSobreTablero(tablero);
    }
}
