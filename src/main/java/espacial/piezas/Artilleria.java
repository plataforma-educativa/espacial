package espacial.piezas;

import espacial.Ataque;

public class Artilleria {

    private int torpedosDeFotones;

    public Artilleria(int cantidadDeTorpedos) {

        torpedosDeFotones = cantidadDeTorpedos;
    }

    public Ataque lanzarAtaque() {

        Ataque ataque;

        if (torpedosDeFotones > 0) {

            ataque = new AtaqueConTorpedoDeFotones();
            torpedosDeFotones--;

        } else {

            ataque = new AtaqueConLaser();
        }

        return ataque;
    }

    public int contarTorpedosDeFotones() {

        return torpedosDeFotones;
    }
}
