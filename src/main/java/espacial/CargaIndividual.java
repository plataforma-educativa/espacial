package espacial;

public class CargaIndividual implements Carga {

    private final int cantidad;
    private SustanciaEspacial sustancia;

    public CargaIndividual(int cantidad, SustanciaEspacial sustancia) {

        this.cantidad = cantidad;
        this.sustancia = sustancia;
    }

    public int obtenerCantidad() {

        return cantidad;
    }

    @Override
    public void subirEn(Transporte unTransporte) {

        sustancia.cargar(cantidad, unTransporte);
    }

    @Override
    public void bajarDe(Transporte unTransporte) {

        sustancia.descargar(cantidad, unTransporte);
    }

    @Override
    public String toString() {

        return String.format("%d %s", cantidad, sustancia);
    }
}
