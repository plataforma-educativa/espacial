package espacial.tableros;

import espacial.Ataque;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public abstract class EstadoDelCasillero {
    
    protected CasilleroInterior contexto;
    
    public EstadoDelCasillero(CasilleroInterior casillero) {

        contexto = casillero;
    }

    protected void cambiarPor(EstadoDelCasillero nuevoEstado) {

        contexto.cambiarA(nuevoEstado);
    }
    
    public abstract EspectroEspacial alEscanear();
 
    public abstract Pieza alObtenerPieza();

    public abstract void alOcuparCon(Pieza unaPieza);
    
    public abstract void alDesocupar();
    
    public abstract void alMoverPiezaA(Casillero destino);
    
    public abstract void alRecibirPiezaDesde(Casillero origen);

    public abstract void alSerAtacadoCon(Ataque unAtaque);
}
