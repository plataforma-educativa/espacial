package espacial.piezas;

import espacial.Accion;
import espacial.Cargamento;

public class Bodega {

    public final Cargamento ANTIMATERIA = crearCargamento();
    public final Cargamento METAL = crearCargamento();
    public final Cargamento CRISTAL = crearCargamento();

    private Accion alCambiarLaCarga = Accion.NINGUNA;

    private final int capacidad;

    public Bodega(int capacidadTotal) {

        capacidad = capacidadTotal;
    }

    public int contabilizarCarga() {

        return ANTIMATERIA.contar() + METAL.contar() + CRISTAL.contar();
    }

    public int contabilizarLugar() {

        return capacidad - contabilizarCarga();
    }

    public int obtenerNivelDeCarga() {

        return (contabilizarCarga() * 100) / capacidad;
    }

    public void cuandoCambiaLaCarga(Accion unaAccion) {

        alCambiarLaCarga = unaAccion;
    }

    private void cambioCarga() {

        alCambiarLaCarga.ejecutar();
    }

    private Cargamento crearCargamento() {

        return new CargamentoObservado(new CargamentoEnBodega(this), this::cambioCarga);
    }
}
