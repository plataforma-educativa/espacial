package espacial.piezas;

import espacial.EspectroEspacial;

public class AgujeroNegroTest implements PruebaSobrePieza<AgujeroNegro> {

    @Override
    public AgujeroNegro piezaCreada() {

        return new AgujeroNegro();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.DESCONOCIDO;
    }
}
