package espacial.piezas.rasgos;

import espacial.Chocable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class NaveChocableTest implements TestDeContrato {

    private int nivelDeEscudosDisminuido;
    private NaveChocable naveChocada;
    private Chocable UN_CHOCABLE = mock(Chocable.class, "UN_CHOCABLE");

    @Test
    void chocoContraUnAsteroide() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnAsteroide();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(25));
    }

    @Test
    void chocoContraUnContenedor() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnContenedor();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(10));
    }

    @Test
    void chocoContraUnaBase() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnaBase();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(5));
    }

    @Test
    void chocoContraUnAgujeroNegro() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnAgujeroNegro();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(75));
    }

    @Test
    void chocoContraElBordeDelTablero() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraElBordeDelTablero();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(50));
    }

    @Test
    void chocoContraUnaNave() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnaNave();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(25));
    }

    @Test
    void fueChocadaPor() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.fueChocadaPor(UN_CHOCABLE);

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(30));
        comprobarQue(unChocableFueNotificadoDelChoqueContraUnaNave());
    }

    private Precondicion laNaveChocadaImplementaNaveChocable() {

        return pre(() -> naveChocada = new NaveChocable() {

            @Override
            public void disminuirNivelDeEscudosEn(int diferencia) {

                nivelDeEscudosDisminuido = diferencia;
            }
        });
    }

    private Postcondicion laNaveChocadaDisminuyoSuNivelDeEscudosEn(int disminucionEsperada) {

        return post(() ->
                assertThat(nivelDeEscudosDisminuido)
                        .as("nivel de escudos disminuído")
                        .isEqualTo(disminucionEsperada));
    }

    private Postcondicion unChocableFueNotificadoDelChoqueContraUnaNave() {

        return post(() -> verify(UN_CHOCABLE).chocoContraUnaNave());
    }
}
