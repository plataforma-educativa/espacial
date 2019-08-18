package espacial.excepciones;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LaNaveNoEstaEnUnCasilleroTest implements TestDeContrato {

    ErrorEspacial unErrorEspacial;

    @Test
    void getMessage() {

        dadoQue(unErrorEspacialFueCreadoSinParametros());

        comprobarQue(elMensajeEsDescriptivo());
    }

    private Precondicion unErrorEspacialFueCreadoSinParametros() {

        return pre(condicion -> unErrorEspacial = new LaNaveNoEstaEnUnCasillero());
    }

    private Postcondicion elMensajeEsDescriptivo() {

        return post(condicion -> assertThat(unErrorEspacial).hasMessage("La Nave no está en un Casillero del Tablero"));
    }

    @Test
    void getMessageIncluyeJustificacion() {

        dadoQue(fueCreadaConUnaJustificacion());

        comprobarQue(incluyeLaJustificacionEnLaDescripcion());
    }

    private Precondicion fueCreadaConUnaJustificacion() {

        return pre(condicion -> unErrorEspacial = new LaNaveNoEstaEnUnCasillero("porque no despegó de la Base"));
    }

    private Postcondicion incluyeLaJustificacionEnLaDescripcion() {

        return post(condicion -> assertThat(unErrorEspacial).hasMessage("La Nave no está en un Casillero del Tablero porque no despegó de la Base"));
    }
}