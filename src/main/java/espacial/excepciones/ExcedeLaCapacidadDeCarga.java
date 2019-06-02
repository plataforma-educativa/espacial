package espacial.excepciones;

public class ExcedeLaCapacidadDeCarga extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 7082584363526498071L;

    public ExcedeLaCapacidadDeCarga(int capacidad, int carga) {

        super("La carga de '%d' excede la capacidad de '%d'", carga, capacidad);
    }
}
