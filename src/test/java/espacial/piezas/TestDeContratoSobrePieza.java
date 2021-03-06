package espacial.piezas;

import espacial.Ataque;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;
import espacial.Partidario;
import espacial.Pieza;
import espacial.test.Postcondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

abstract class TestDeContratoSobrePieza<T extends Pieza> implements TestDeContrato {

    final NaveEspacial NAVE_ESPACIAL = mock(NaveEspacial.class, "NAVE_ESPACIAL");
    final Ataque UN_ATAQUE = mock(Ataque.class, "UN_ATAQUE");
    final Pieza.Visitante UN_VISITANTE = mock(Pieza.Visitante.class, "UN_VISITANTE");
    final Partidario.Condicional SEGUN_ES_PARTIDARIO = mock(Partidario.Condicional.class, "SEGUN_ES_PARTIDARIO");

    abstract T piezaCreada();

    abstract EspectroEspacial espectroEsperado();

    abstract Postcondicion evaluoLaCondicionDePartidarioEsperada();

    abstract Postcondicion laNaveEspacialFueNotificadaDelChoque();

    @Test
    void escanear() {

        Pieza pieza = piezaCreada();

        EspectroEspacial espectro = pieza.escanear();

        comprobarQue(elEspectroEscaneadoEsElEsperado(espectro));
    }

    private Postcondicion elEspectroEscaneadoEsElEsperado(EspectroEspacial espectro) {

        return post(condicion -> assertThat(espectro).isEqualTo(espectroEsperado()));
    }

    @Test
    void fueChocadaPor() {

        Pieza pieza = piezaCreada();

        pieza.fueChocadaPor(NAVE_ESPACIAL);

        comprobarQue(laNaveEspacialFueNotificadaDelChoque());
    }

    @Test
    void obtenerPuntosParaCualquierPieza() {

        Pieza unaPieza = piezaCreada();

        comprobarQue(sePuedenObtenerLosPuntosDe(unaPieza));
    }

    private Postcondicion sePuedenObtenerLosPuntosDe(Pieza unaPieza) {

        return post(condicion ->

                assertThat(unaPieza.obtenerPuntos())
                        .as("puntos")
                        .isBetween(Pieza.PUNTOS_MINIMOS, Pieza.PUNTOS_MAXIMOS));
    }

    @Test
    void tieneToString() {

        Pieza unaPieza = piezaCreada();

        comprobarQue(esAutoDescriptiva(unaPieza));
    }

    private Postcondicion esAutoDescriptiva(Pieza unaPieza) {

        return post(condicion -> assertThat(unaPieza).hasToString("Pieza<" + espectroEsperado() + ">"));
    }

    @Test
    void evaluarCondicionDePartidario() {

        Pieza pieza = piezaCreada();

        pieza.evaluar(SEGUN_ES_PARTIDARIO);

        comprobarQue(evaluoLaCondicionDePartidarioEsperada());
    }

    protected Postcondicion evaluoQueEsAliado() {

        return post(condicion -> verify(SEGUN_ES_PARTIDARIO).siEsAliado());
    }

    protected Postcondicion evaluoQueEsNeutral() {

        return post(condicion -> verify(SEGUN_ES_PARTIDARIO).siEsNeutral());
    }

    protected Postcondicion evaluoQueEsRival() {

        return post(condicion -> verify(SEGUN_ES_PARTIDARIO).siEsRival());
    }
}
