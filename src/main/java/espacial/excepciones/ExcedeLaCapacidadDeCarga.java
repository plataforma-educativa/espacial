package espacial.excepciones;

public class ExcedeLaCapacidadDeCarga extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 7082584363526498071L;

    public ExcedeLaCapacidadDeCarga(int capacidad, int cargaTotal) {

        super("'%d' excede la capacidad de carga de '%d'", cargaTotal, capacidad);
    }
}
