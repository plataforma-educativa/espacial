package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeMetal;

public class ContenedorDeMetal extends  ContenedorDeSustancia implements DepositoDeMetal {

    private final Cargamento metal = crearCargamento();

    public ContenedorDeMetal() {

    }

    public ContenedorDeMetal(int cantidadInicial) {

        cargarMetal(cantidadInicial);
    }

    @Override
    public Cargamento obtenerMetal() {

        return metal;
    }
}
