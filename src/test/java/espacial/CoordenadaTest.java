package espacial;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class CoordenadaTest implements Prueba {

    @Test
    public void crearConFilaColumna() {
        
        Coordenada coordenada = new Coordenada(5, 3);
        
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
        
        Coordenada coordenada = new Coordenada(2, 4);
        
        assertThat(coordenada.equals(coordenada)).isTrue();
    }
    
    @Test
    public void sonIgualesSiCoincideFilaColumna() {
        
        Coordenada unaCoordenada = new Coordenada(3, 9);
        Coordenada otraCoordenada = new Coordenada(3, 9);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isTrue();
        assertThat(otraCoordenada.equals(unaCoordenada)).isTrue();
    }
    
    @Test
    public void noSonIgualesSiSoloCoincideFila() {
        
        Coordenada unaCoordenada = new Coordenada(5, 8);
        Coordenada otraCoordenada = new Coordenada(5, -6);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isFalse();
        assertThat(otraCoordenada.equals(unaCoordenada)).isFalse();
    }
    
    @Test
    public void noSonIgualesSiSoloCoincideLaColumna() {
        
        Coordenada unaCoordenada = new Coordenada(2, 5);
        Coordenada otraCoordenada = new Coordenada(-1, 5);
        
        assertThat(unaCoordenada.equals(otraCoordenada)).isFalse();
        assertThat(otraCoordenada.equals(unaCoordenada)).isFalse();
    }
    
    @Test
    public void siSonIgualesTieneElMismoHash() {
        
        Coordenada unaCoordenada = new Coordenada(2, 5);
        Coordenada otraCoordenada = new Coordenada(2, 5);

        assertThat(unaCoordenada).hasSameHashCodeAs(otraCoordenada);
    }
}
