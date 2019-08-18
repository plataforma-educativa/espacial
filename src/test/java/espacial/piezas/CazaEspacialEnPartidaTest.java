package espacial.piezas;

import espacial.Casillero;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

abstract class CazaEspacialEnPartidaTest implements TestDeContrato {

    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    private CazaEspacial unCazaEspacial;

    protected abstract CazaEspacial crear();

    @Test
    void cuandoSeQuedaSinPuntosSeSacaDelTablero() {

        dadoQue(fueCreadoUnCazaEspacialColocandoloEnUnCasillero());

        cuandoUnCazaSeQuedaSinPuntos();

        comprobarQue(seDesocupoElCasilleroQueOcupaba());
    }

    private Precondicion fueCreadoUnCazaEspacialColocandoloEnUnCasillero() {

        return pre(condicion -> {

            unCazaEspacial = crear();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private void cuandoUnCazaSeQuedaSinPuntos() {

        unCazaEspacial.disminuirNivelDeEscudosEn(100);
    }

    private Postcondicion seDesocupoElCasilleroQueOcupaba() {

        return  post(condicion -> verify(UN_CASILLERO).desocupar());
    }
}
