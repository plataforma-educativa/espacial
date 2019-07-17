package espacial.piezas;

import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

class PiezaAtacableEnPartidaTest implements TestDeContrato {

    @Nested
    class SiEsUnCazaEspacial extends TestDePiezaAtacableEnPartida<CazaEspacial> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return pre(condicion ->  unaPiezaAtacable = new CazaEspacial());
        }
    }

    @Nested
    class SiEsUnaBaseEspacial extends TestDePiezaAtacableEnPartida<BaseEspacial> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return pre(condicion ->  unaPiezaAtacable = new BaseEspacial());
        }
    }

    @Nested
    class SiEsUnAsteroide extends TestDePiezaAtacableEnPartida<Asteroide> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return pre(condicion ->  unaPiezaAtacable = new Asteroide(100));
        }
    }

    @Nested
    class SiEsUnContenedorDeAntimateria extends TestDePiezaAtacableEnPartida<ContenedorDeAntimateria> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return pre(condicion ->  unaPiezaAtacable = new ContenedorDeAntimateria());
        }
    }

    @Nested
    class SiEsUnContenedorDeMetalDePieza extends TestDePiezaAtacableEnPartida<ContenedorDeMetal> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return pre(condicion ->  unaPiezaAtacable = new ContenedorDeMetal());
        }
    }

    @Nested
    class SiEsUnContenedorDeCristal extends TestDePiezaAtacableEnPartida<ContenedorDeCristal> {

        @Override
        protected Precondicion fueCreadaLaPieza() {

            return pre(condicion ->  unaPiezaAtacable = new ContenedorDeCristal());
        }
    }

}
