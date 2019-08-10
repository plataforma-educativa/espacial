package espacial.partidas;

import espacial.Atacable;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class FabricaDePiezasTest implements TestDeContrato {

    private FabricaDePiezas unaFabrica;

    private Pieza piezaCreada;

    private Set<Integer> cargas = new HashSet<>();

    private Set<Integer> durezas = new HashSet<>();

    @Test
    void crearContenedorDeAntimateria() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearContenedorDeAntimateria();

        comprobarQue(unaPiezaEsUnContenedorCon(SustanciaEspacial.ANTIMATERIA));
    }

    @Test
    void crearContenedorDeMetal() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearContenedorDeMetal();

        comprobarQue(unaPiezaEsUnContenedorCon(SustanciaEspacial.METAL));
    }

    @Test
    void crearContenedorDeCristal() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearContenedorDeCristal();

        comprobarQue(unaPiezaEsUnContenedorCon(SustanciaEspacial.CRISTAL));
    }

    private Precondicion fueCreadaUnaFabricaDePiezas() {

        return pre(condicion -> unaFabrica = FabricaDePiezas.crear());
    }

    private Postcondicion unaPiezaEsUnContenedorCon(SustanciaEspacial sustancia) {

        return post(condicion -> {

            assertThat(piezaCreada)
                    .as("Pieza creada").isNotNull()
                    .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.CONTENEDOR);

            assertThat(piezaCreada.buscar(sustancia))
                    .as("buscar(ANTIMATERIA)")
                    .isGreaterThan(0);
        });
    }

    @Test
    void crearContenedorDeAntimateriaConCargaAleatoria() {

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

        return post(condicion -> assertThat(cargas.size() > 10).as("tiene cargas diferentes").isTrue());
    }

    @Test
    void crearContenedorDeMetalConCargaAleatoria() {

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
    void crearContenedorDeCristalConCargaAleatoria() {

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
    void crear() {

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

        return post(condicion -> assertThat(cargas).allMatch(carga -> (carga >= desde && carga <= hasta)));
    }

    @Test
    void crearAsteroide() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearAsteoride();

        comprobarQue(unaPiezasEsUnAsteoride());
    }

    private Postcondicion unaPiezasEsUnAsteoride() {

        return post(condicion ->

                assertThat(piezaCreada)
                        .as("Pieza creada").isNotNull()
                        .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.ASTEROIDE)
        );
    }

    @Test
    void crearAsteroidesConDurezaAleatoria() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        alCrearMultiplesAsteroides();

        comprobarQue(lasDurezasDeLasPiezasCreadasSonDiferentes());
    }

    private void alCrearMultiplesAsteroides() {

        for (int i = 0; i < 10_000; i++) {

            Pieza asteroide = unaFabrica.crearAsteoride();

            int puntosIniciales = asteroide.obtenerPuntos();
            asteroide.fueAtacadoCon(Atacable::atacadoConLaser);
            int puntosFinales = asteroide.obtenerPuntos();

            durezas.add(puntosFinales - puntosIniciales);
        }
    }

    private Postcondicion lasDurezasDeLasPiezasCreadasSonDiferentes() {

        return post(condicion ->

                assertThat(durezas.size() > 10)
                        .as("las durezas son diferentes")
                        .isTrue()
        );
    }

    @Test
    void crearAgujeroNegro() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearAgujeroNegro();

        comprobarQue(laPiezaCreadaEsUnAgujeroNegro());
    }

    private Postcondicion laPiezaCreadaEsUnAgujeroNegro() {

        return post(condicion ->

                assertThat(piezaCreada).as("pieza creada").isNotNull()
                        .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.DESCONOCIDO)
        );
    }

    @Test
    void crearNaveEspacial() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearNaveEspacial();

        comprobarQue(laPiezaCreadaEsUnaNave());
    }

    private Postcondicion laPiezaCreadaEsUnaNave() {

        return post(condicion -> {

            assertThat(piezaCreada).as("pieza creada").isNotNull()
                    .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.NAVE);

            assertThat(NaveEspacial.class.cast(piezaCreada)).as("nave")
                    .extracting(NaveEspacial::nombrar).isNotNull();
        });
    }

    @Test
    void crearBaseEspacial() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearBaseEspacial();

        comprobarQue(laPiezaCreadaEsUnaBase());

    }

    private Postcondicion laPiezaCreadaEsUnaBase() {

        return post(condicion ->

                assertThat(piezaCreada).as("pieza creada").isNotNull()
                        .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.BASE)
        );
    }

    @Test
    void crearBaseDesconocida() {

        dadoQue(fueCreadaUnaFabricaDePiezas());

        piezaCreada = unaFabrica.crearBaseDesconocida();

        comprobarQue(laPiezaCreadaEsUnaBaseDesconocida());
        comprobarQue(laPiezaCreadaTieneAlMenosUnaSustancia());
    }

    private Postcondicion laPiezaCreadaEsUnaBaseDesconocida() {

        return post(condicion ->

                assertThat(piezaCreada).as("pieza creada").isNotNull()
                        .extracting(Pieza::escanear).isEqualTo(EspectroEspacial.BASE)
        );
    }

    private Postcondicion laPiezaCreadaTieneAlMenosUnaSustancia() {

        return post(condicion ->

                assertThat(
                        piezaCreada.buscar(SustanciaEspacial.ANTIMATERIA) +
                        piezaCreada.buscar(SustanciaEspacial.METAL) +
                        piezaCreada.buscar(SustanciaEspacial.CRISTAL))
                        .as("sustancia")
                        .isGreaterThan(0)
        );
    }
}