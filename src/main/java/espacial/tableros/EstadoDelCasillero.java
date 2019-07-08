package espacial.tableros;

import espacial.Ataque;
import espacial.Carga;
import espacial.Casillero;
import espacial.ConsumidorDeCasilleros;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.SustanciaEspacial;

public abstract class EstadoDelCasillero {
    
    protected final CasilleroInterior contexto;
    
    public EstadoDelCasillero(CasilleroInterior casillero) {

        contexto = casillero;
    }

    protected void cambiarPor(EstadoDelCasillero nuevoEstado) {

        contexto.cambiarA(nuevoEstado);
    }
    
    public abstract EspectroEspacial alEscanear();

    public abstract int alBuscar(SustanciaEspacial unaSustancia);

    public abstract Pieza alObtenerPieza();

    public abstract void alOcuparCon(Pieza unaPieza);
    
    public abstract void alDesocupar();
    
    public abstract void alMoverPiezaA(Casillero destino);
    
    public abstract void alRecibirPiezaDesde(Casillero origen);

    public abstract void alSerAtacadoCon(Ataque unAtaque);

    public abstract void alEntregar(Carga unaCarga);

    public abstract void alRecibir(Carga unaCarga);

    public abstract void alAceptar(ConsumidorDeCasilleros unConsumidor);
}
