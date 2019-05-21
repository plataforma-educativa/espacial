package espacial.piezas;

import static org.mockito.Mockito.*;

import espacial.Casillero;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

public class CazaEspacialEnPartidaTest implements Prueba {

    private final Casillero UN_CASILLERO = mock(Casillero.class, "UN_CASILLERO");

    private CazaEspacial unCazaEspacial;

    @Test
    public void cuandoSeQuedaSinPuntosSeSacaDelTablero() {

        dadoQue(fueCreadoUnCazaEspacialColocandoloEnUnCasillero());

        cuandoUnCazaSeQuedaSinPuntos();

        comprobarQue(seDesocupoElCasilleroQueOcupaba());
    }

    private Precondicion fueCreadoUnCazaEspacialColocandoloEnUnCasillero() {

        return precondicion("fue creado unCazaEspacial, colocándolo en UN_CASILLERO", () -> {

            unCazaEspacial = new CazaEspacial();
            unCazaEspacial.fueColocadaEn(UN_CASILLERO);
        });
    }

    private void cuandoUnCazaSeQuedaSinPuntos() {

        unCazaEspacial.disminuirNivelDeEscudosEn(100);
    }

    private Postcondicion seDesocupoElCasilleroQueOcupaba() {

        return  postcondicion("se desocupó el casillero que ocupaba", () -> {

            verify(UN_CASILLERO).desocupar();
        });
    }
}
