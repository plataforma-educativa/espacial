package espacial.tableros;

import espacial.Casillero;
import espacial.Pieza;
import espacial.Tablero;

public interface TableroContenedor extends Tablero {

    void fueAgregadaEn(Casillero casillero, Pieza unaPieza);
}
