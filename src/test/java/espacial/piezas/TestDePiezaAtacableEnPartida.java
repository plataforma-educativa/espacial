package espacial.piezas;

import espacial.Casillero;
import espacial.piezas.rasgos.PiezaAtacable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public abstract class TestDePiezaAtacableEnPartida<T extends PiezaAtacable> implements TestDeContrato {

    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    protected T unaPiezaAtacable;

    protected abstract Precondicion fueCreadaLaPieza();

    @Test
    public void cuandoSeQuedaSinPuntosSeSacaDelTablero() {

        dadoQue(fueCreadaLaPieza());
        dadoQue(unPiezaFueColocadaEnUnCasillero());

        cuandoUnaPiezaEsDestruida();

        comprobarQue(seDesocupoElCasilleroQueOcupaba());
    }

    private Precondicion unPiezaFueColocadaEnUnCasillero() {

        return precondicion(() -> unaPiezaAtacable.fueColocadaEn(UN_CASILLERO));
    }

    private void cuandoUnaPiezaEsDestruida() {

        unaPiezaAtacable.decrementarPuntosEn(unaPiezaAtacable.obtenerPuntos());
    }

    private Postcondicion seDesocupoElCasilleroQueOcupaba() {

        return  postcondicion(() -> verify(UN_CASILLERO).desocupar());
    }
}
