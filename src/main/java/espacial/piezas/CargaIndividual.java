package espacial.piezas;

import espacial.Carga;
import espacial.Deposito;
import espacial.SustanciaEspacial;

import java.util.Objects;

public class CargaIndividual implements Carga {

    private final int cantidad;
    private final SustanciaEspacial sustancia;

    public CargaIndividual(int cantidad, SustanciaEspacial sustancia) {

        this.cantidad = cantidad;
        this.sustancia = sustancia;
    }

    public int obtenerCantidad() {

        return cantidad;
    }

    @Override
    public void subirEn(Deposito unDeposito) {

        sustancia.cargar(cantidad, unDeposito);
    }

    @Override
    public void bajarDe(Deposito unDeposito) {

        sustancia.descargar(cantidad, unDeposito);
    }

    @Override
    public String toString() {

        return String.format("%d %s", cantidad, sustancia);
    }

    @Override
    public boolean equals(Object otro) {

        boolean iguales = (this == otro);

        if (!iguales && otro instanceof CargaIndividual) {

            CargaIndividual otraCargaIndividual = CargaIndividual.class.cast(otro);

            iguales = (cantidad == otraCargaIndividual.cantidad) &&
                    (sustancia == otraCargaIndividual.sustancia);
        }

        return iguales;
    }

    @Override
    public int hashCode() {

        return Objects.hash(cantidad, sustancia);
    }
}
