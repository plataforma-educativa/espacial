package espacial.excepciones;

public class NoExisteBatallaEspacial extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = -8499158720816997349L;

    public NoExisteBatallaEspacial() {
        
        super("No fue creada una instancia de BatallaEspacial");
    }
}
