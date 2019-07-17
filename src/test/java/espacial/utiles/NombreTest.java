package espacial.utiles;

import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NombreTest implements TestDeContrato {

    private Nombre unNombre;

    @Test
    void tieneToStringDescriptivo() {

        dadoQue(fueCreadoUnNombre("Picard II", "Jean-Luc Picard, capitán histórcio del USS Enterprise-D"));

        comprobarQue(sePuedeUtilizarEnLosMensajes("'Picard II'"));
    }

    private Postcondicion sePuedeUtilizarEnLosMensajes(String toStringEsperado) {

        return post(condicion -> assertThat(unNombre).hasToString(toStringEsperado));
    }

    private Precondicion fueCreadoUnNombre(String valor, String explicacion) {

        return pre(condicion -> unNombre = Nombre.sera(valor).porque(explicacion).obtener());
    }
}