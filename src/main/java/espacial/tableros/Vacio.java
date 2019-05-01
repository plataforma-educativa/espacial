package espacial.tableros;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

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
        
    }

    @Override
    public void alMoverPiezaA(Casillero destino) {
        
    }

    @Override
    public void alRecibirPiezaDesde(Casillero origen) {

        Pieza pieza = origen.obtenerPieza();
        origen.desocupar();
        contexto.ocuparCon(pieza);
    }

    @Override
    public Pieza alObtenerPieza() {

        return null;
    }

}
