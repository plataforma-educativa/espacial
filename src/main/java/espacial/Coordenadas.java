package espacial;

import java.util.Objects;

public class Coordenadas {

    private final int fila;
    private final int columna;

    public static Coordenadas con(int fila, int columna) {

        return new Coordenadas(fila, columna);
    }

    private Coordenadas(int fila, int columna) {

        this.fila = fila;
        this.columna = columna;
    }

    public int obtenerFila() {

        return fila;
    }

    public int obtenerColumna() {

        return columna;
    }

    @Override
    public boolean equals(Object otroObjeto) {

        boolean iguales = (this == otroObjeto);

        if (!iguales && otroObjeto instanceof Coordenadas) {

            Coordenadas otraCoordenadas = (Coordenadas) otroObjeto;

            iguales = (this.fila == otraCoordenadas.fila) &&
                    (this.columna == otraCoordenadas.columna);
        }

        return iguales;
    }

    @Override
    public int hashCode() {

        return Objects.hash(fila, columna);
    }

    @Override
    public String toString() {

        return "[" + fila + "][" + columna + "]";
    }

    /**
     * @return número que identifica la coordenada asignada con un patrón en espiral, iniciando en [0][0].
     *
     *      -5  -4  -3  -2  -1  [0]  1   2   3   4   5   6
     *
     *  5  100  99  98  97  96  95  94  93  92  91  90
     *  4  101  64  63  62  61  60  59  58  57  56  89
     *  3  102  65  36  35  34  33  32  31  30  55  88 129
     *  2  103  66  37  16  15  14  13  12  29  54  87 128
     *  1  104  67  38  17   4   3   2  11  28  53  86 127
     * [0] 105  68  39  18   5  [0]  1  10  27  52  85 126
     * -1  106  69  40  19   6   7   8   9  26  51  84 125
     * -2  107  70  41  20  21  22  23  24  25  50  83 124
     * -3  108  71  42  43  44  45  46  47  48  49  82 123
     * -4  109  72  73  74  75  76  77  78  79  80  81 122
     * -5  110 111 112 113 114 115 116 117 118 119 120 121
     */
    public int numerar() {

        final int absFila = Math.abs(fila);
        final int absColumna = Math.abs(columna);
        final int nivel = Math.max(absFila, absColumna);
        final int desplazamiento;
        final int coeficiente;

        if (nivel == absFila) {
            if (fila > 0) {
                desplazamiento = -columna;
                coeficiente = -1;
            } else {
                desplazamiento = columna;
                coeficiente = 3;
            }
        } else {
            if (columna > 0) {
                desplazamiento = fila;
                coeficiente = -3;
            } else {
                desplazamiento = -fila;
                coeficiente = 1;
            }
        }

        return nivel * (4 * nivel + coeficiente) + desplazamiento;
    }

    @FunctionalInterface
    public interface Consumidor {

        void aceptar(int fila, int columna);
    }
}
