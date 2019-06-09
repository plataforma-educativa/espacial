package espacial.excepciones;

import espacial.Carga;
import espacial.Pieza;

public class NoPuedeExtraerUnaCarga extends ErrorEnLaBatallaEspacial {

    private static final long serialVersionUID = 4025811230145729830L;

    public NoPuedeExtraerUnaCarga(Pieza unaPieza, Carga unaCarga) {

        super("%s no puede extraer una carga de '%s'", unaPieza.escanear(), unaCarga);
    }
}
