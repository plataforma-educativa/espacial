package espacial.excepciones;

public class ExcedeLaCargaDisponible extends ErrorEspacial {

    private static final long serialVersionUID = 7082184353524498271L;

    public ExcedeLaCargaDisponible(int disponible, int carga) {

        super("'%d' excede la carga disponible de '%d'", carga, disponible);
    }
}
