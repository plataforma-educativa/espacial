package espacial;

import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoordenadaTest implements TestDeContrato {

    @Test
    void crearConFilaColumna() {
        
        Coordenada coordenada = Coordenada.con(5, 3);
        
        comprobarQue(fueInicializada(coordenada, 5, 3));
    }

    private Postcondicion fueInicializada(Coordenada coordenada, int fila, int columna) {

        return post(() -> {
          
            assertThat(coordenada.obtenerFila()).as("fila").isEqualTo(5);
            assertThat(coordenada.obtenerColumna()).as("columna").isEqualTo(3);
        });
    }
    
    @Test
    void sonIgualesSiSonElMismoObjeto() {
        
        Coordenada coordenada = Coordenada.con(2, 4);
        
        assertThat(coordenada.equals(coordenada)).isTrue();
    }
    
    @Test
    void sonIgualesSiCoincideFilaColumna() {
        
        Coordenada unaCoordenada = Coordenada.con(3, 9);
        Coordenada otraCoordenada = Coordenada.con(3, 9);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isTrue();
        assertThat(otraCoordenada.equals(unaCoordenada)).isTrue();
    }
    
    @Test
    void noSonIgualesSiSoloCoincideFila() {
        
        Coordenada unaCoordenada = Coordenada.con(5, 8);
        Coordenada otraCoordenada = Coordenada.con(5, -6);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isFalse();
        assertThat(otraCoordenada.equals(unaCoordenada)).isFalse();
    }
    
    @Test
    void noSonIgualesSiSoloCoincideLaColumna() {
        
        Coordenada unaCoordenada = Coordenada.con(2, 5);
        Coordenada otraCoordenada = Coordenada.con(-1, 5);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isFalse();
        assertThat(otraCoordenada.equals(unaCoordenada)).isFalse();
    }
    
    @Test
    void siSonIgualesTieneElMismoHash() {
        
        Coordenada unaCoordenada = Coordenada.con(2, 5);
        Coordenada otraCoordenada = Coordenada.con(2, 5);

        assertThat(unaCoordenada).hasSameHashCodeAs(otraCoordenada);
    }
}
