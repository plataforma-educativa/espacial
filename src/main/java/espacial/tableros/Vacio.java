package espacial.tableros;

import espacial.Ataque;
import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.excepciones.Defecto;

public class Vacio extends EstadoDelCasillero {

    public Vacio(CasilleroInterior contexto) {
        
        super(contexto);
    }

    @Override
    public EspectroEspacial alEscanear() {
        
        return EspectroEspacial.VACIO;
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {

        OcuparCasilleroVacio ocuparCasilleroVacio = new OcuparCasilleroVacio(contexto);

        unaPieza.aceptar(ocuparCasilleroVacio);

        cambiarPor(ocuparCasilleroVacio.obtenerEstadoResultante());
    }

    @Override
    public void alDesocupar() {
        
        throw new Defecto("No se puede desocupar un Casillero Vacío");
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
        throw new Defecto("No se puede mover una Pieza desde un Casillero Vacío");
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {

        Pieza pieza = origen.obtenerPieza();
        origen.desocupar();
        contexto.ocuparCon(pieza);
    }

    @Override
    public void alSerAtacadoCon(Ataque unAtaque) {

    }

    @Override
    public Pieza alObtenerPieza() {

        throw new Defecto("No se puede obtenerEstadoResultante Pieza en un Casillero Vacío");
    }
}
