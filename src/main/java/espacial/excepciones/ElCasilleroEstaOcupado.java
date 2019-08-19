package espacial.excepciones;

import espacial.Casillero;

public class ElCasilleroEstaOcupado extends ErrorEspacial {

    private static final long serialVersionUID = 915899178995204747L;

    private static final String MENSAJE = "El Casillero está ocupado: %s";

    public ElCasilleroEstaOcupado(Casillero casillero) {

        super(MENSAJE, casillero);
    }
}
