package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeAntimateria;

public class ContenedorDeAntimateria extends ContenedorDeSustancia implements DepositoDeAntimateria {

    private final Cargamento antimateria = new CargamentoDeSustancia(obtenerCapacidad());

    public ContenedorDeAntimateria() {

    }

    public ContenedorDeAntimateria(int cantidadInicial) {

        this.cargarAntimateria(cantidadInicial);
    }

    @Override
    public Cargamento obtenerAntimateria() {

        return antimateria;
    }
}
