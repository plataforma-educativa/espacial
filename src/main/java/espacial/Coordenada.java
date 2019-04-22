package espacial;

import java.util.Objects;

public class Coordenada {

    private final int fila;
    private final int columna;
    
    public static Coordenada con(int fila, int columna) {

        return new Coordenada(fila, columna);
    }
    
    private Coordenada(int fila, int columna) {

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
        
        if (!iguales && otroObjeto instanceof Coordenada) {
            
            Coordenada otraCoordenada = (Coordenada) otroObjeto;
            
            iguales = (this.fila == otraCoordenada.fila) &&
                      (this.columna == otraCoordenada.columna);
        }
        
        return iguales;
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(fila, columna);
    }
}
