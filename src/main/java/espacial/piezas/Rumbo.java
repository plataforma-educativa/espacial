package espacial.piezas;

import espacial.Direccion;
import espacial.utiles.Accion;

public class Rumbo {

    private Direccion direccion = Direccion.NORTE;
    private Accion alCambiar = Accion.NINGUNA;

    public Direccion obtener() {

        return direccion;
    }

    public void tomar(Direccion direccionElegida) {

        if (direccion != direccionElegida) {

            direccion = direccionElegida;

            alCambiar.ejecutar();
        }
    }

    public void cuandoCambia(Accion unaAccion) {

        alCambiar = unaAccion;
    }
}
