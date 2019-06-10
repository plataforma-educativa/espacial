package espacial.piezas;

public class CargamentoDeSustancia extends CargamentoFinito {

    private final int capacidad;

    public CargamentoDeSustancia(int capacidadTotal) {

        capacidad = capacidadTotal;
    }

    @Override
    protected int contarLugar() {

        return capacidad - contar();
    }
}
