package espacial.piezas;

import espacial.Cargamento;
import espacial.excepciones.ExcedeLaCapacidadDeCarga;
import espacial.excepciones.ExcedeLaCargaDisponible;

public class Bodega {

    public final Cargamento ANTIMATERIA = new CargamentoEnBodega();
    public final Cargamento METAL = new CargamentoEnBodega();
    public final Cargamento CRISTAL = new CargamentoEnBodega();

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

    private class CargamentoEnBodega implements Cargamento {

        private int cantidad;

        @Override
        public void agregar(int cantidadAgregada) {

            if (cantidadAgregada > contabilizarLugar()) {

                throw new ExcedeLaCapacidadDeCarga(capacidad, cantidadAgregada);
            }

            cantidad += cantidadAgregada;
        }

        @Override
        public void retirar(int cantidadRetirada) {

            if (cantidadRetirada > cantidad) {

                throw new ExcedeLaCargaDisponible(cantidad, cantidadRetirada);
            }

            cantidad -= cantidadRetirada;
        }

        @Override
        public int contar() {

            return cantidad;
        }
    }
}
