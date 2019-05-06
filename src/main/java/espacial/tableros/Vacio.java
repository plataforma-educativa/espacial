package espacial.tableros;

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

        cambiarPor(new Ocupado(contexto, unaPieza));
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
    public Pieza alObtenerPieza() {

        throw new Defecto("No se puede obtener Pieza en un Casillero Vacío");
    }
}