package espacial.piezas;

import espacial.Ataque;
import espacial.utiles.Accion;

public class Artilleria {

    private int torpedosDeFotones;
    private Accion alCambiarLasMuniciones = Accion.NINGUNA;

    public Artilleria(int cantidadDeTorpedos) {

        torpedosDeFotones = cantidadDeTorpedos;
    }

    public Ataque lanzarAtaque() {

        Ataque ataque;

        if (tieneTorpedos()) {

            ataque = new AtaqueConTorpedoDeFotones();
            consumirUnTorpedo();

        } else {

            ataque = new AtaqueConLaser();
        }

        return ataque;
    }

    private boolean tieneTorpedos() {

        return torpedosDeFotones > 0;
    }

    private void consumirUnTorpedo() {

        torpedosDeFotones--;
        alCambiarLasMuniciones.ejecutar();
    }

    public int contarTorpedosDeFotones() {

        return torpedosDeFotones;
    }

    public void cuandoCambianLasMuniciones(Accion accion) {

        alCambiarLasMuniciones = accion;
    }
}
