package espacial.partidas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FabricaDePiezasTest implements TestDeContrato {

    private FabricaDePiezas unaFabrica;

    private Pieza piezaCreada;

    @Test
    public void crearContenedorDeAntimateria() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearContenedorDeAntimateria();

        comprobarQue(unaPiezaEsUnContenedorCon(SustanciaEspacial.ANTIMATERIA));
    }

    @Test
    public void crearContenedorDeMetal() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearContenedorDeMetal();

        comprobarQue(unaPiezaEsUnContenedorCon(SustanciaEspacial.METAL));
    }

    @Test
    public void crearContenedorDeCristal() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearContenedorDeCristal();

        comprobarQue(unaPiezaEsUnContenedorCon(SustanciaEspacial.CRISTAL));
    }

    private Precondicion fueCreadaUnaFabricaDePiezas() {

        return precondicion(() -> unaFabrica = new FabricaDePiezas());
    }

    private Postcondicion unaPiezaEsUnContenedorCon(SustanciaEspacial sustancia) {

        return postcondicion(() -> {

            assertThat(piezaCreada)
                    .as("Pieza creada").isNotNull()
                    .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.CONTENEDOR);

            assertThat(piezaCreada.buscar(sustancia))
                    .as("buscar(ANTIMATERIA)")
                    .isGreaterThan(0);
        });
    }
}