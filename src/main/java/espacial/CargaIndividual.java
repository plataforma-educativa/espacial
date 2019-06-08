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
    public void subirEn(Deposito unDeposito) {

        sustancia.cargar(cantidad, unDeposito);
    }

    @Override
    public void bajarDe(Deposito unDeposito) {

        sustancia.descargar(cantidad, unDeposito);
    }

    @Override
    public String toString() {

        return String.format("%d %s", cantidad, sustancia);
    }
}
