package espacial.piezas;

import espacial.Ataque;

public class Arsenal {

    private int torpedos;

    public Arsenal(int cantidadDeTorpedos) {

        torpedos = cantidadDeTorpedos;
    }

    public Ataque lanzarAtaque() {

        Ataque ataque;

        if (torpedos > 0) {

            ataque = new AtaqueConTorpedoDeFotones();
            torpedos--;

        } else {

            ataque = new AtaqueConLaser();
        }

        return ataque;
    }
}
