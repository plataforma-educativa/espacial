package espacial;

import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoordenadasTest implements TestDeContrato {

    @Test
    void crearConFilaColumna() {
        
        Coordenadas coordenadas = Coordenadas.con(5, 3);
        
        comprobarQue(fueInicializada(coordenadas, 5, 3));
    }

    private Postcondicion fueInicializada(Coordenadas coordenadas, int fila, int columna) {

        return post(() -> {
          
            assertThat(coordenadas.obtenerFila()).as("fila").isEqualTo(5);
            assertThat(coordenadas.obtenerColumna()).as("columna").isEqualTo(3);
        });
    }
    
    @Test
    void sonIgualesSiSonElMismoObjeto() {
        
        Coordenadas coordenadas = Coordenadas.con(2, 4);
        
        assertThat(coordenadas.equals(coordenadas)).isTrue();
    }
    
    @Test
    void sonIgualesSiCoincideFilaColumna() {
        
        Coordenadas unaCoordenadas = Coordenadas.con(3, 9);
        Coordenadas otraCoordenadas = Coordenadas.con(3, 9);
        
        assertThat(unaCoordenadas.equals(otraCoordenadas)).isTrue();
        assertThat(otraCoordenadas.equals(unaCoordenadas)).isTrue();
    }
    
    @Test
    void noSonIgualesSiSoloCoincideFila() {
        
        Coordenadas unaCoordenadas = Coordenadas.con(5, 8);
        Coordenadas otraCoordenadas = Coordenadas.con(5, -6);
        
        assertThat(unaCoordenadas.equals(otraCoordenadas)).isFalse();
        assertThat(otraCoordenadas.equals(unaCoordenadas)).isFalse();
    }
    
    @Test
    void noSonIgualesSiSoloCoincideLaColumna() {
        
        Coordenadas unaCoordenadas = Coordenadas.con(2, 5);
        Coordenadas otraCoordenadas = Coordenadas.con(-1, 5);
        
        assertThat(unaCoordenadas.equals(otraCoordenadas)).isFalse();
        assertThat(otraCoordenadas.equals(unaCoordenadas)).isFalse();
    }
    
    @Test
    void siSonIgualesTieneElMismoHash() {
        
        Coordenadas unaCoordenadas = Coordenadas.con(2, 5);
        Coordenadas otraCoordenadas = Coordenadas.con(2, 5);

        assertThat(unaCoordenadas).hasSameHashCodeAs(otraCoordenadas);
    }

    @Test
    void tieneToString() {

        Coordenadas coordenadas = Coordenadas.con(23, 78);

        assertThat(coordenadas).hasToString("[23][78]");
    }
}
