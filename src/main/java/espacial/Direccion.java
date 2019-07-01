package espacial;


public enum Direccion {
    
    NORTE ( 1,  0), 
    SUR   (-1,  0), 
    ESTE  ( 0,  1), 
    OESTE ( 0, -1);

    private final int versorFila;
    private final int versorColumna;
    
    Direccion(int versorFila, int versorColumna) {
     
        this.versorFila = versorFila;
        this.versorColumna = versorColumna;
    }
    
    public Coordenadas trasladar(Coordenadas coordenadas) {
        
        return Coordenadas.con(coordenadas.obtenerFila()    + versorFila,
                             coordenadas.obtenerColumna() + versorColumna);
    }
}
