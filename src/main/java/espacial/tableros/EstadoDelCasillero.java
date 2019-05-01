package espacial.tableros;

import espacial.Casillero;
import espacial.EspectroEspacial;
import espacial.Pieza;

public interface EstadoDelCasillero {

    EspectroEspacial escanear();
 
    Pieza obtenerPieza();

    void ocuparCon(Pieza unaPieza);
    
    void desocupar();
    
    void moverPiezaA(Casillero destino);
    
    void recibirPiezaDesde(Casillero origen);

}
