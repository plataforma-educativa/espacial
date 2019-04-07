
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BatallaEspacialTest {

    @Test
    public void crearUnObjetoDeTipoBatallaEspacialDejandolaRegistrada() {

        BatallaEspacial batallaEspacial = new BatallaEspacial();
        
        comprobarQueQuedoRegistrada(batallaEspacial);
    }
    
    private void comprobarQueQuedoRegistrada(BatallaEspacial objeto) {
        
        assertThat(BatallaEspacial.obtener()).isSameAs(objeto);
    }
}
