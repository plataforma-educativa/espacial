package espacial.partidas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class FabricaDePiezasTest implements TestDeContrato {

    private FabricaDePiezas unaFabrica;

    private Pieza piezaCreada;

    private Set<Integer> cargas = new HashSet<>();

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

        return precondicion(() -> unaFabrica = FabricaDePiezas.crear());
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

    @Test
    public void crearContenedorDeAntimateriaConCargaAleatoria() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        alCrearMultiplesContenedoresDeAntimateria();

        comprobarQue(lasCargasDeLasPiezasCreadasSonDiferentes());
    }

    private void alCrearMultiplesContenedoresDeAntimateria() {

        for (int i = 0; i < 100; i++) {

            cargas.add(unaFabrica.crearContenedorDeAntimateria().buscar(SustanciaEspacial.ANTIMATERIA));
        }
    }

    private Postcondicion lasCargasDeLasPiezasCreadasSonDiferentes() {

        return postcondicion(() -> assertThat(cargas.size() > 10).as("tiene cargas diferentes").isTrue());
    }

    @Test
    public void crearContenedorDeMetalConCargaAleatoria() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        alCrearMultiplesContenedoresDeMetal();

        comprobarQue(lasCargasDeLasPiezasCreadasSonDiferentes());
    }

    private void alCrearMultiplesContenedoresDeMetal() {

        for (int i = 0; i < 100; i++) {

            cargas.add(unaFabrica.crearContenedorDeMetal().buscar(SustanciaEspacial.METAL));
        }
    }

    @Test
    public void crearContenedorDeCristalConCargaAleatoria() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        alCrearMultiplesContenedoresDeCristal();

        comprobarQue(lasCargasDeLasPiezasCreadasSonDiferentes());
    }

    private void alCrearMultiplesContenedoresDeCristal() {

        for (int i = 0; i < 100; i++) {

            cargas.add(unaFabrica.crearContenedorDeCristal().buscar(SustanciaEspacial.CRISTAL));
        }
    }

    @Test
    public void crear() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        alCrearMultiplesContenedores();

        comprobarQue(lasCargasDeLasPiezasCreadasEstanEnElRango(1, 250));
    }

    private void alCrearMultiplesContenedores() {

        for (int i = 0; i < 1000; i++) {

            cargas.add(unaFabrica.crearContenedorDeAntimateria().buscar(SustanciaEspacial.ANTIMATERIA));
            cargas.add(unaFabrica.crearContenedorDeMetal().buscar(SustanciaEspacial.METAL));
            cargas.add(unaFabrica.crearContenedorDeCristal().buscar(SustanciaEspacial.CRISTAL));
        }
    }

    private Postcondicion lasCargasDeLasPiezasCreadasEstanEnElRango(int desde, int hasta) {

        return postcondicion(() -> assertThat(cargas).allMatch(carga -> (carga >= desde && carga <= hasta)));
    }
}