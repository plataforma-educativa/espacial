package espacial.interfaz.componentes;

import espacial.Direccion;
import javafx.application.Platform;
import javafx.beans.value.ObservableValueBase;

public class RotacionSegunDireccion extends ObservableValueBase<Double> implements Direccion.Condicional {

    private double valor;

    @Override
    public Double getValue() {

        return valor;
    }

    private void setValor(double nuevoValor) {

        valor = nuevoValor;

        Platform.runLater(this::fireValueChangedEvent);
    }

    @Override
    public void siEsNorte() {

        setValor(0);
    }

    @Override
    public void siEsSur() {

        setValor(180);
    }

    @Override
    public void siEsEste() {

        setValor(90);
    }

    @Override
    public void siEsOeste() {

        setValor(-90);
    }
}