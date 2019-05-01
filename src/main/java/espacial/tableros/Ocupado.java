package espacial.tableros;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public class Ocupado extends EstadoDelCasillero {

    private Pieza pieza;
    
    public Ocupado(CasilleroInterior contexto, Pieza porPieza) {

        super(contexto);
        pieza = porPieza;
    }
    
    @Override
    public EspectroEspacial alEscanear() {
        
        return pieza.escanear();
    }

    @Override
    public void alOcuparCon(Pieza unaPieza) {

        cambiarPor(new Ocupado(contexto, unaPieza));
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

        origen.obtenerPieza().chocarCon(pieza);
    }

    @Override
    public Pieza alObtenerPieza() {

        return pieza;
    }

}
