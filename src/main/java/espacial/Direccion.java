package espacial;


import espacial.utiles.AccionSobre;

public enum Direccion {

    NORTE(1, 0, Condicional::siEsNorte),
    SUR(-1, 0, Condicional::siEsSur),
    ESTE(0, 1, Condicional::siEsEste),
    OESTE(0, -1, Condicional::siEsOeste);

    private final int versorFila;
    private final int versorColumna;
    private final AccionSobre<Condicional> alEvaluar;

    Direccion(int versorFila, int versorColumna, AccionSobre<Condicional> alEvaluar) {

        this.versorFila = versorFila;
        this.versorColumna = versorColumna;
        this.alEvaluar = alEvaluar;
    }

    public Coordenadas trasladar(Coordenadas coordenadas) {

        return Coordenadas.con(coordenadas.obtenerFila() + versorFila,
                coordenadas.obtenerColumna() + versorColumna);
    }

    public void evaluar(Condicional condicional) {

        alEvaluar.ejecutar(condicional);
    }

    public interface Condicional {

        default void siEsNorte() {
        }

        default void siEsSur() {
        }

        default void siEsEste() {
        }

        default void siEsOeste() {
        }
    }
}
