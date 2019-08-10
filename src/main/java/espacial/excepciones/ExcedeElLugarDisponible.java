package espacial.excepciones;

public class ExcedeElLugarDisponible extends ErrorEspacial {

    private static final long serialVersionUID = 7082584363526498071L;

    public ExcedeElLugarDisponible(int lugar, int carga) {

        super("'%d' excede el lugar disponible de '%d'", carga, lugar);
    }
}
