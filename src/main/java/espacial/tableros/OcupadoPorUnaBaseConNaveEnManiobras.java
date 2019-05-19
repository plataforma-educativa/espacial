package espacial.tableros;

import espacial.Ataque;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public class OcupadoPorUnaBaseConNaveEnManiobras extends EstadoDelCasillero {

    private Pieza base;
    private Pieza nave;
    
    public OcupadoPorUnaBaseConNaveEnManiobras(CasilleroInterior casillero,
                                               Pieza base,
                                               Pieza nave) {

        super(casillero);
        this.base = base;
        this.nave = nave;
    }

    @Override
    public EspectroEspacial alEscanear() {
        
        return base.escanear();
    }

    @Override
    public Pieza alObtenerPieza() {
    
        return nave;
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {

        unaPieza.chocarCon(nave);
    }

    @Override
    public void alDesocupar() {
        
        cambiarPor(new OcupadoPorUnaBase(contexto, base));
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
        destino.recibirPiezaDesde(contexto);
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {
        
        origen.obtenerPieza().chocarCon(nave);
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

        nave.fueAtacadoCon(unAtaque);
    }

}
