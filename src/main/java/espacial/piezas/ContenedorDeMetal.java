package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeMetal;

public class ContenedorDeMetal extends  ContenedorDeSustancia implements DepositoDeMetal {

    private final Cargamento metal = new Cargamento(obtenerCapacidad());

    @Override
    public Cargamento obtenerMetal() {

        return metal;
    }
}
