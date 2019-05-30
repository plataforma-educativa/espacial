package espacial.util;

import static org.assertj.core.api.Assertions.*;

import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class OpcionalTest {

    @Test
    public void conValor() {

        Opcional<Integer> opcional = Opcional.con(5);

        assertThat(opcional).isNotNull();
    }

    @Test
    public void vacio() {

        Opcional<String> opcional = Opcional.vacio();

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
