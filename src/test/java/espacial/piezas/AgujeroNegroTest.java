package espacial.piezas;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import org.junit.jupiter.api.Test;

public class AgujeroNegroTest extends PruebaSobrePieza<AgujeroNegro> {

    private AgujeroNegro unAgujeroNegro;

    @Override
    public AgujeroNegro piezaCreada() {

        return new AgujeroNegro();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.DESCONOCIDO;
    }

    @Override
    public Postcondicion laNaveEspacialFueNotificadaDelChoque() {

        return postcondicion("notificó a la NaveEspacial que chocó contra un AgujeroNegro", () -> {
            
            verify(NAVE_ESPACIAL).chocoContraUnAgujeroNegro();
        });
    }

    @Test
    public void obtenerPuntos() {

        dadoQue(fueCreadoUnAgujeroNegro());

        comprobarQue(losPuntosUnAgujeroNegroSonMaximos());

    }

    private Precondicion fueCreadoUnAgujeroNegro() {

        return precondicion("fue creado unAgujeroNegro", () -> {

            unAgujeroNegro = new AgujeroNegro();
        });
    }

    private Postcondicion losPuntosUnAgujeroNegroSonMaximos() {

        return postcondicion("los puntos unAgujeroNegro son máximos", () -> {

            assertThat(unAgujeroNegro.obtenerPuntos()).as("puntos")
                    .isEqualTo(Pieza.PUNTOS_MAXIMOS);

        });
    }
}
