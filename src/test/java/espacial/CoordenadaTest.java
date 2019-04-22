package espacial;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class CoordenadaTest implements Prueba {

    @Test
    public void crearConFilaColumna() {
        
        Coordenada coordenada = Coordenada.con(5, 3);
        
        comprobarQue(fueInicializada(coordenada, 5, 3));
    }

    private Postcondicion fueInicializada(Coordenada coordenada, int fila, int columna) {

        return postcondicion("fue inicializada", () -> {
          
            assertThat(coordenada.obtenerFila()).as("fila").isEqualTo(5);
            assertThat(coordenada.obtenerColumna()).as("columna").isEqualTo(3);
        });
    }
    
    @Test
    public void sonIgualesSiSonElMismoObjeto() {
        
        Coordenada coordenada = Coordenada.con(2, 4);
        
        assertThat(coordenada.equals(coordenada)).isTrue();
    }
    
    @Test
    public void sonIgualesSiCoincideFilaColumna() {
        
        Coordenada unaCoordenada = Coordenada.con(3, 9);
        Coordenada otraCoordenada = Coordenada.con(3, 9);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isTrue();
        assertThat(otraCoordenada.equals(unaCoordenada)).isTrue();
    }
    
    @Test
    public void noSonIgualesSiSoloCoincideFila() {
        
        Coordenada unaCoordenada = Coordenada.con(5, 8);
        Coordenada otraCoordenada = Coordenada.con(5, -6);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isFalse();
        assertThat(otraCoordenada.equals(unaCoordenada)).isFalse();
    }
    
    @Test
    public void noSonIgualesSiSoloCoincideLaColumna() {
        
        Coordenada unaCoordenada = Coordenada.con(2, 5);
        Coordenada otraCoordenada = Coordenada.con(-1, 5);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isFalse();
        assertThat(otraCoordenada.equals(unaCoordenada)).isFalse();
    }
    
    @Test
    public void siSonIgualesTieneElMismoHash() {
        
        Coordenada unaCoordenada = Coordenada.con(2, 5);
        Coordenada otraCoordenada = Coordenada.con(2, 5);

        assertThat(unaCoordenada).hasSameHashCodeAs(otraCoordenada);
    }
}
