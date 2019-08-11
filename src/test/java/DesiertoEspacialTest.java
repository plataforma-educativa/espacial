import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class DesiertoEspacialTest implements TestDeContrato {

    private final Nave UNA_NAVE = mock(Nave.class, "UNA_NAVE");

    private DesiertoEspacial desierto;

    @Test
    void crearUnObjetoDeTipoBatallaEspacialDejandolaRegistrada() {

        DesiertoEspacial desiertoEspacial = new DesiertoEspacial();

        /*
        comprobarQue(quedoRegistrada(desiertoEspacial));
         */

    }

    private Postcondicion quedoRegistrada(BatallaEspacial objeto) {

        return post(condicion -> assertThat(BatallaEspacial.obtener()).isSameAs(objeto));
    }


}
