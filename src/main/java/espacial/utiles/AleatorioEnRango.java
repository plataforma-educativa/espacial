package espacial.utiles;

class AleatorioEnRango extends Aleatorio<Integer> {

    private final int valorInicial;
    private final int valorFinal;

    public AleatorioEnRango(int desde, int hasta) {

        valorInicial = desde;
        valorFinal = hasta;
    }

    @Override
    public Integer obtener() {

        return implementacion.nextInt(valorFinal - valorInicial + 1) + valorInicial;
    }
}
