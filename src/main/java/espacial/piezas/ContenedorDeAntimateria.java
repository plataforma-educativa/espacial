package espacial.piezas;

import espacial.EspectroEspacial;
import espacial.Pieza;
import espacial.PiezaMovil;
import espacial.Visitante;

public class ContenedorDeAntimateria implements Pieza {

    @Override
    public EspectroEspacial escanear() {
        
        return EspectroEspacial.CONTENEDOR;
    }
    
    @Override
    public void fueChocadaPor(PiezaMovil otraPieza) {

        otraPieza.chocoContraUnContenedor();
    }

    @Override
    public void aceptar(Visitante visitante) {

        visitante.siEsContenedor(this);
    }
}
