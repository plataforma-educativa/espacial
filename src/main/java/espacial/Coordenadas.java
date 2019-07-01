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
}
