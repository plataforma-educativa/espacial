package espacial.piezas;

import espacial.Cargamento;

public class Bodega {

    public final Cargamento ANTIMATERIA = new CargamentoEnBodega(this);
    public final Cargamento METAL = new CargamentoEnBodega(this);
    public final Cargamento CRISTAL = new CargamentoEnBodega(this);

    private final int capacidad;

    public Bodega(int capacidadTotal) {

        capacidad = capacidadTotal;
    }

    public int contabilizarCarga() {

        return ANTIMATERIA.contar() + METAL.contar() + CRISTAL.contar();
    }

    public int contabilizarLugar() {

        return capacidad - contabilizarCarga();
    }
}
