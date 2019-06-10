package espacial.piezas;

import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

public class PiezaAtacableEnPartidaTest implements TestDeContrato {

    @Nested
    public class SiEsUnCazaEspacial extends TestDePiezaAtacableEnPartida<CazaEspacial> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return precondicion(() -> unaPiezaAtacable = new CazaEspacial());
        }
    }

    @Nested
    public class SiEsUnaBaseEspacial extends TestDePiezaAtacableEnPartida<BaseEspacial> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return precondicion(() -> unaPiezaAtacable = new BaseEspacial());
        }
    }

    @Nested
    public class SiEsUnAsteroide extends TestDePiezaAtacableEnPartida<Asteroide> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return precondicion(() -> unaPiezaAtacable = new Asteroide());
        }
    }

    @Nested
    public class SiEsUnContenedorDeAntimateria extends TestDePiezaAtacableEnPartida<ContenedorDeAntimateria> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return precondicion(() -> unaPiezaAtacable = new ContenedorDeAntimateria());
        }
    }

    @Nested
    public class SiEsUnContenedorDeMetalDePieza extends TestDePiezaAtacableEnPartida<ContenedorDeMetal> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return precondicion(() -> unaPiezaAtacable = new ContenedorDeMetal());
        }
    }

    @Nested
    public class SiEsUnContenedorDeCristal extends TestDePiezaAtacableEnPartida<ContenedorDeCristal> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return precondicion(() -> unaPiezaAtacable = new ContenedorDeCristal());
        }
    }

}
