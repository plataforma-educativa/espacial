package espacial.piezas;

import espacial.EspectroEspacial;

public class ContenedorDeAntimateriaTest implements PruebaSobrePieza<ContenedorDeAntimateria> {

    @Override
    public ContenedorDeAntimateria piezaCreada() {

        return new ContenedorDeAntimateria();
    }

    @Override
    public EspectroEspacial espectroEsperado() {

        return EspectroEspacial.CONTENEDOR;
    }
}
