import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;

import static org.assertj.core.api.Assertions.*;

public class NaveTest implements Prueba {

    private BatallaEspacial batallaEspacial;
    
    @Test
    public void crearUnObjetoDeTipoNaveDejandoloEnLaBase() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        
        Nave unaNave = new Nave();
        
        comprobarQue(existeEnLaBase(unaNave));
    }

    private Postcondicion existeEnLaBase(Nave unaNave) {
        
        return postcondicion("existe en la Base una Nave", () -> {
            
            assertThat(batallaEspacial.obtenerNaves()).as("naves de la BatallaEspacial").hasSize(1);
            assertThat(batallaEspacial.obtenerNaves()[0]).as("primer Nave en la BatallaEspacial").isSameAs(unaNave);
        });
    }
    
    private Precondicion fueCreadaLaBatallaEspacial() {
        
        return precondicion("fue creada la BatallaEspacial", () -> {
           
            batallaEspacial = new BatallaEspacial();
        });
    }
    
}
