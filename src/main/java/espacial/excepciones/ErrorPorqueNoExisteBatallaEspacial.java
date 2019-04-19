package espacial.excepciones;

public class ErrorPorqueNoExisteBatallaEspacial extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = -8499158720816997349L;

    public ErrorPorqueNoExisteBatallaEspacial() {
        super("No fue creada una instancia de BatallaEspacial");
    }
}
