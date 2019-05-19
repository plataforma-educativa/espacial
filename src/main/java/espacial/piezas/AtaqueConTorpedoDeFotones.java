package espacial.piezas;

import espacial.Atacable;
import espacial.Ataque;

public class AtaqueConTorpedoDeFotones implements Ataque {

    @Override
    public void aplicarSobre(Atacable atacable) {

        atacable.atacadoConTorpedoDeFotones();
    }
}
