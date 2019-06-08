package espacial.utiles;

import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OpcionalTest implements TestDeContrato {

    @Test
    public void conValor() {

        Opcional<Integer> opcional = Opcional.con(5);

        assertThat(opcional).isNotNull();
    }

    @Test
    public void vacio() {

        Opcional<String> opcional = Opcional.sinValor();

        assertThat(opcional).isNotNull();
    }

    @Test
    public void obtenerPeroSiNoExisteLanzar() {

        final String ACTIVADO = "activado";

        Opcional<String> configuracion = Opcional.con(ACTIVADO);

        String valor = configuracion.obtenerPeroSiNoExisteLanzar(LaNaveNoEstaEnUnCasillero::new);

        assertThat(valor).isEqualTo(ACTIVADO);
    }

    @Test
    public void obtenerPeroSiNoExisteLanzarCuandoNoTieneValor() {

        Opcional<String> configuracion = Opcional.con(null);

        assertThatThrownBy(() -> configuracion.obtenerPeroSiNoExisteLanzar(LaNaveNoEstaEnUnCasillero::new))
                .isInstanceOf(LaNaveNoEstaEnUnCasillero.class);
    }
}
