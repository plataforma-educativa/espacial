package espacial.piezas.rasgos;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import espacial.Chocable;
import espacial.test.Postcondicion;
import espacial.test.Precondicion;
import espacial.test.Prueba;
import org.junit.jupiter.api.Test;

public class NaveChocableTest implements Prueba {

    private int nivelDeEscudosDisminuido;
    private NaveChocable naveChocada;
    private Chocable UN_CHOCABLE = mock(Chocable.class, "UN_CHOCABLE");

    @Test
    public void chocoContraUnAsteroide() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnAsteroide();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(25));
    }

    @Test
    public void chocoContraUnContenedor() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnContenedor();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(10));
    }

    @Test
    public void chocoContraUnaBase() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnaBase();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(5));
    }

    @Test
    public void chocoContraUnAgujeroNegro() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnAgujeroNegro();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(75));
    }

    @Test
    public void chocoContraElBordeDelTablero() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraElBordeDelTablero();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(50));
    }

    @Test
    public void chocoContraUnaNave() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.chocoContraUnaNave();

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(25));
    }

    @Test
    public void fueChocadaPor() {

        dadoQue(laNaveChocadaImplementaNaveChocable());

        naveChocada.fueChocadaPor(UN_CHOCABLE);

        comprobarQue(laNaveChocadaDisminuyoSuNivelDeEscudosEn(30));
        comprobarQue(unChocableFueNotificadoDelChoqueContraUnaNave());
    }

    private Precondicion laNaveChocadaImplementaNaveChocable() {

        return precondicion("la naveChocada implementa NaveChocable", () -> {

            naveChocada = new NaveChocable() {

                @Override
                public void disminuirNivelDeEscudosEn(int diferencia) {

                    nivelDeEscudosDisminuido = diferencia;
                }
            };
        });
    }

    private Postcondicion laNaveChocadaDisminuyoSuNivelDeEscudosEn(int disminucionEsperada) {

        return postcondicion("la naveChocada disminuyó su nivel de escudos en " + disminucionEsperada, () -> {

            assertThat(nivelDeEscudosDisminuido).as("nivel de escudos disminuído")
                    .isEqualTo(disminucionEsperada);
        });
    }

    private Postcondicion unChocableFueNotificadoDelChoqueContraUnaNave() {

        return postcondicion("UN_CHOCABLE fue notificado del choque contre una Nave", () -> {

            verify(UN_CHOCABLE).chocoContraUnaNave();
        });
    }
}
