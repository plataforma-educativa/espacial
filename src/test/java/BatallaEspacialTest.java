
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import espacial.test.Postcondicion;
import espacial.test.Prueba;

public class BatallaEspacialTest implements Prueba {

    @Test
    public void crearUnObjetoDeTipoBatallaEspacialDejandolaRegistrada() {

        BatallaEspacial batallaEspacial = new BatallaEspacial();
        
        comprobarQue(quedoRegistrada(batallaEspacial));
    }
    
    private Postcondicion quedoRegistrada(BatallaEspacial objeto) {
        
        return postcondicion("quedó registrada la Batalla Espacial", () -> {

            assertThat(BatallaEspacial.obtener()).isSameAs(objeto);
        });
    }
    
}
