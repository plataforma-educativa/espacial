package espacial.tableros;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public interface EstadoDelCasillero {

    EspectroEspacial escanear();
 
    Pieza obtenerPieza();

    EstadoDelCasillero ocuparCon(Pieza unaPieza);
    
    EstadoDelCasillero desocupar();
    
    EstadoDelCasillero moverPiezaA(Casillero destino);
    
    EstadoDelCasillero recibirPiezaDesde(Casillero origen);

}
