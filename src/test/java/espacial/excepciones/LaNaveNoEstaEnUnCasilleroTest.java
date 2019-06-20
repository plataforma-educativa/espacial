package espacial.excepciones;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LaNaveNoEstaEnUnCasilleroTest {

    @Test
    void getMessage() {

        assertThat(new LaNaveNoEstaEnUnCasillero())
                .hasNoCause()
                .hasMessage("La Nave no está en un Casillero del Tablero");
    }

    @Test
    void getMessageIncluyeJustificacion() {

        assertThat(new LaNaveNoEstaEnUnCasillero("porque no despegó de la Base"))
                .hasNoCause()
                .hasMessage("La Nave no está en un Casillero del Tablero porque no despegó de la Base");
    }

}