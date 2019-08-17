package espacial.tableros;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;

public class OcuparCasilleroVacio implements Pieza.Visitante {

    private final CasilleroInterior casillero;
    private EstadoDelCasillero estado;

    public OcuparCasilleroVacio(CasilleroInterior casillero) {

        this.casillero = casillero;
    }

    @Override
    public void siEsBase(BaseEspacial pieza) {

        estaOcupadoPorUnaBase(pieza);
    }

    @Override
    public void siEsNave(NaveEspacial pieza) {

        estaOcupado(pieza);
    }

    @Override
    public void siEsContenedor(Pieza pieza) {

        estaOcupado(pieza);
    }

    @Override
    public void siEsAsteroide(Pieza pieza) {

        estaOcupado(pieza);
    }

    @Override
    public void siEsAgujeroNegro(Pieza pieza) {

        estaOcupado(pieza);
    }

    private void estaOcupado(Pieza pieza) {

        estado = new Ocupado(casillero, pieza);
    }

    private void estaOcupadoPorUnaBase(Pieza pieza) {

        estado = new OcupadoPorUnaBase(casillero, pieza);
    }

    public EstadoDelCasillero obtenerEstadoResultante() {

        return estado;
    }
}
