package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeAntimateria;

public class ContenedorDeAntimateria extends ContenedorDeSustancia implements DepositoDeAntimateria {

    private final Cargamento antimateria = new Cargamento(obtenerCapacidad());

    @Override
    public Cargamento obtenerAntimateria() {

        return antimateria;
    }
}
