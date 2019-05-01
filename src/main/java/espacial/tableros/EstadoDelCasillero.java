package espacial.tableros;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public interface EstadoDelCasillero {

    EspectroEspacial alEscanear();
 
    Pieza alObtenerPieza();

    void alOcuparCon(Pieza unaPieza);
    
    void alDesocupar();
    
    void alMoverPiezaA(Casillero destino);
    
    void alRecibirPiezaDesde(Casillero origen);

}
