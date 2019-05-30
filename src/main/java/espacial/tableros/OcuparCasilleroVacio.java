package espacial.tableros;

import espacial.Pieza;
import espacial.Visitante;

public class OcuparCasilleroVacio implements Visitante {

    private final CasilleroInterior casillero;
    private EstadoDelCasillero estado;

    public OcuparCasilleroVacio(CasilleroInterior casillero) {

        this.casillero = casillero;
    }

    @Override
    public void siEsBase(Pieza pieza) {

        estaOcupadoPorUnaBase(pieza);
    }

    @Override
    public void siEsNave(Pieza pieza) {

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
