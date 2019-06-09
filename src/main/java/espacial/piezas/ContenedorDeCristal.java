package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeCristal;

public class ContenedorDeCristal extends ContenedorDeSustancia implements DepositoDeCristal {

    private final Cargamento cristal = new Cargamento(obtenerCapacidad());

    @Override
    public Cargamento obtenerCristal() {

        return cristal;
    }
}

