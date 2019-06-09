package espacial.piezas.rasgos;

public interface NaveDeCarga extends PiezaDeposito, DepositoDeAntimateria, DepositoDeMetal, DepositoDeCristal {

    default int obtenerCapacidad() {

        return 100;
    }
}
