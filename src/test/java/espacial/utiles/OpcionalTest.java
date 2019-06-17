package espacial.utiles;

import espacial.excepciones.LaNaveNoEstaEnUnCasillero;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OpcionalTest implements TestDeContrato {

    @Test
    void conValor() {

        Opcional<Integer> opcional = Opcional.con(5);

        assertThat(opcional).isNotNull();
    }

    @Test
    void vacio() {

        Opcional<String> opcional = Opcional.sinValor();

        assertThat(opcional).isNotNull();
    }

    @Test
    void obtenerPeroSiNoExisteLanzar() {

        final String ACTIVADO = "activado";

        Opcional<String> configuracion = Opcional.con(ACTIVADO);

        String valor = configuracion.obtenerPeroSiNoExisteLanzar(LaNaveNoEstaEnUnCasillero::new);

        assertThat(valor).isEqualTo(ACTIVADO);
    }

    @Test
    void obtenerPeroSiNoExisteLanzarCuandoNoTieneValor() {

        Opcional<String> configuracion = Opcional.con(null);

        assertThatThrownBy(() -> configuracion.obtenerPeroSiNoExisteLanzar(LaNaveNoEstaEnUnCasillero::new))
                .isInstanceOf(LaNaveNoEstaEnUnCasillero.class);
    }
}
