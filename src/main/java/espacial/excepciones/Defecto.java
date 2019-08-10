package espacial.excepciones;

public class Defecto extends ErrorEspacial {

    private static final long serialVersionUID = 4799227336999502123L;

    public Defecto(String descripcion) {

        super(descripcion);
    }

    public Defecto(String descripcion, Throwable causa) {

        super(descripcion, causa);
    }
}
