package espacial;

public enum SustanciaEspacial {

    ANTIMATERIA;

    public Carga por(int cantidad) {

        return new Carga(cantidad);
    }
}
