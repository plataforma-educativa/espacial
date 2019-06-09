package espacial.piezas.rasgos;

public interface BaseDeposito extends PiezaDeposito, DepositoDeAntimateria, DepositoDeMetal, DepositoDeCristal {

    default int obtenerCapacidad() {

        return 5000;
    }
}
