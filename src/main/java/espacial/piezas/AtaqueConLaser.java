package espacial.piezas;

import espacial.Atacable;
import espacial.Ataque;

public class AtaqueConLaser implements Ataque {

    @Override
    public void aplicarSobre(Atacable atacable) {

        atacable.atacadoConLaser();
    }
}
