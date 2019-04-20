import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;

import static org.assertj.core.api.Assertions.*;
import static espacial.test.Aserciones.assertThat;

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
            
            assertThat(batallaEspacial.obtenerNaves()).as("naves de la BatallaEspacial")
                .hasSize(1)
                .containsExactly(unaNave);
            
            assertThat(batallaEspacial.obtenerTablero())
                .tieneNave()
                    .en(0,0);
        });
    }
    
    private Precondicion fueCreadaLaBatallaEspacial() {
        
        return precondicion("fue creada la BatallaEspacial", () -> {
           
            batallaEspacial = new BatallaEspacial();
        });
    }
    
    @Test
    public void crearTresObjetosDeTipoNaveDejandolosEnLaBase() {
        
        dadoQue(fueCreadaLaBatallaEspacial());
        
        Nave primerNave = new Nave();
        Nave segundaNave = new Nave();
        Nave tercerNave = new Nave();
        
        comprobarQue(existenEnLaBase(primerNave, segundaNave, tercerNave));
    }

    private Postcondicion existenEnLaBase(Nave primerNave, Nave segundaNave, Nave tercerNave) {

        return postcondicion("existen en la Base tres Naves", () -> {
          
            assertThat(batallaEspacial.obtenerNaves()).as("naves de la BatallaEspacial")
                .hasSize(3)
                .containsExactly(primerNave, segundaNave, tercerNave);
        });
    }
    
}
