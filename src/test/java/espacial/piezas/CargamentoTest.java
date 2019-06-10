package espacial.piezas;

import espacial.test.Precondicion;
import espacial.test.TestDeContrato;
import org.junit.jupiter.api.Nested;

public class CargamentoTest implements TestDeContrato {

    @Nested
    public class DeSustancia extends TestDeCargamentoLimitado {

        @Override
        protected Precondicion fueCreadoUnCargamento(int capacidad) {

            return precondicion(() -> unCargamento = new espacial.piezas.CargamentoDeSustancia(capacidad));
        }
    }

    @Nested
    public class EnBodega extends TestDeCargamentoLimitado {

        @Override
        protected Precondicion fueCreadoUnCargamento(int capacidad) {

            return precondicion(() -> {

                Bodega bodega = new Bodega(capacidad);
                unCargamento = new espacial.piezas.CargamentoEnBodega(bodega);

            });
        }
    }
}
