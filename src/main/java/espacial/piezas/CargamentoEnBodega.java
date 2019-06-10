package espacial.piezas;

public class CargamentoEnBodega extends CargamentoFinito {

    private final Bodega bodega;

    public CargamentoEnBodega(Bodega unaBodega) {

        bodega = unaBodega;
    }

    @Override
    protected int contarLugar() {

        return bodega.contabilizarLugar();
    }
}
