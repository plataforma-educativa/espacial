package espacial;

public class Carga {

    private final int cantidad;
    private SustanciaEspacial sustancia;

    public Carga(int cantidad, SustanciaEspacial sustancia) {

        this.cantidad = cantidad;
        this.sustancia = sustancia;
    }

    public int obtenerCantidad() {

        return cantidad;
    }

    @Override
    public String toString() {

        return String.format("%d %s", cantidad, sustancia);
    }
}
