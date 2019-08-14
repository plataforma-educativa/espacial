package espacial.excepciones;

public class ParametroInvalido extends ErrorEspacial {

    private static final long serialVersionUID = -2304637086814842858L;

    public ParametroInvalido(String descripcion) {

        super(descripcion);
    }

    public ParametroInvalido(String descripcion, Object... valores) {

        super(descripcion, valores);
    }

}
