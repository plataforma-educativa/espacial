package espacial.piezas;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import espacial.Accion;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

public class IndicadorTest implements Prueba {

    private final Accion UNA_ACCION = mock(Accion.class, "UNA_ACCION");
    private Indicador unIndicador;

    @Test
    public void crearConUnValor() {

        unIndicador = new Indicador(300);

        comprobarQue(elValorInicialDeUnIndicadorEs(300));
    }

    private Postcondicion elValorInicialDeUnIndicadorEs(int esperado) {

        return postcondicion("el valor inicial de unIndicador es " + esperado, () -> {

            assertThat(unIndicador.obtenerValor()).as("valor").isEqualTo(esperado);
        });
    }

    @Test
    public void decrementar() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(100));

        unIndicador.decrementarEn(40);

        comprobarQue(elValorDeUnIndicadorEs(60));
    }

    private Precondicion fueCreadoUnIndicadorConValorInicial(int valor) {

        return precondicion("fue creado un indicador con valor incial " + valor, () -> {

            unIndicador = new Indicador(valor);
        });
    }

    private Postcondicion elValorDeUnIndicadorEs(int esperado) {

        return postcondicion("el valor de unIndicador es " + esperado, () -> {

            assertThat(unIndicador.obtenerValor()).as("valor").isEqualTo(esperado);
        });
    }

    @Test
    public void decrementarMasDelValorInicial() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(50));

        unIndicador.decrementarEn(51);

        comprobarQue(elValorDeUnIndicadorEs(0));
    }

    @Test
    public void cuandoSeAgota() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(80));

        unIndicador.cuandoSeAgota(UNA_ACCION);
        unIndicador.decrementarEn(30);
        unIndicador.decrementarEn(50);

        comprobarQue(ejecutoUnaAccionConfigurada());
    }

    private Postcondicion ejecutoUnaAccionConfigurada() {

        return postcondicion("ejecutó UNA_ACCION configurada", () -> {

            verify(UNA_ACCION).ejecutar();
        });
    }

    @Test
    public void cuandoSeAgotaSiNoTieneConfiguradaUnaAccion() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(25));

        unIndicador.decrementarEn(25);
    }

    @Test
    public void cuandoSeAgotaAlDecrementarHabiendoseAgotadoAntes() {

        dadoQue(fueCreadoUnIndicadorConValorInicial(40));

        unIndicador.cuandoSeAgota(UNA_ACCION);
        unIndicador.decrementarEn(40);
        unIndicador.decrementarEn(60);

        comprobarQue(soloSeEjecutoUnaAccionUnaVez());
    }

    private Postcondicion soloSeEjecutoUnaAccionUnaVez() {

        return postcondicion("solo se ejecutó UNA_ACCION una vez", () -> {

            verify(UNA_ACCION, times(1)).ejecutar();
        });
    }

}
