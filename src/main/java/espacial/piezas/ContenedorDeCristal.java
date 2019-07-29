package espacial.piezas;

import espacial.Cargamento;
import espacial.piezas.rasgos.DepositoDeCristal;

public class ContenedorDeCristal extends ContenedorDeSustancia implements DepositoDeCristal {

    private final Cargamento cristal = crearCargamento();

    public ContenedorDeCristal() {

    }

    public ContenedorDeCristal(int cantidadInicial) {

        cargarCristal(cantidadInicial);
    }


    @Override
    public Cargamento obtenerCristal() {

        return cristal;
    }
}

