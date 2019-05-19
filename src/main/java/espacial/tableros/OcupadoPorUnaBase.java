package espacial.tableros;

import espacial.Ataque;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public class OcupadoPorUnaBase extends EstadoDelCasillero {

    private Pieza base;
    
    public OcupadoPorUnaBase(CasilleroInterior casillero, Pieza base) {

        super(casillero);
        this.base = base;
    }

    @Override
    public EspectroEspacial alEscanear() {

        return base.escanear();
    }

    @Override
    public Pieza alObtenerPieza() {

        return base;
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {
        
        cambiarPor(new OcupadoPorUnaBaseConNaveEnManiobras(contexto, base, unaPieza));
    }

    @Override
    public void alDesocupar() {
        
        cambiarPor(new Vacio(contexto));
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
        destino.recibirPiezaDesde(contexto);
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {
        
        Pieza pieza = origen.obtenerPieza();
        origen.desocupar();
        cambiarPor(new OcupadoPorUnaBaseConNaveEnManiobras(contexto, base, pieza));
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

        base.fueAtacadoCon(unAtaque);
    }
}
