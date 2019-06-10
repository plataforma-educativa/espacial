package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeCristal;

public class ContenedorDeCristal extends ContenedorDeSustancia implements DepositoDeCristal {

    private final Cargamento cristal = new CargamentoDeSustancia(obtenerCapacidad());

    @Override
    public Cargamento obtenerCristal() {

        return cristal;
    }
}

