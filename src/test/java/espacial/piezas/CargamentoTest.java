package espacial.piezas;

import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

class CargamentoTest implements TestDeContrato {

    @Nested
    class DeSustancia extends TestDeCargamentoLimitado {

        @Override
        protected Precondicion fueCreadoUnCargamento(int capacidad) {

            return pre(condicion ->  unCargamento = new espacial.piezas.CargamentoDeSustancia(capacidad));
        }
    }

    @Nested
    class EnBodega extends TestDeCargamentoLimitado {

        @Override
        protected Precondicion fueCreadoUnCargamento(int capacidad) {

            return pre(condicion ->  {

                Bodega bodega = new Bodega(capacidad);
                unCargamento = new espacial.piezas.CargamentoEnBodega(bodega);

            });
        }
    }
}
