package espacial;


public enum Direccion {
    
    NORTE ( 1,  0), 
    SUR   (-1,  0), 
    ESTE  ( 0,  1), 
    OESTE ( 0, -1);

    private final int versorFila;
    private final int versorColumna;
    
    private Direccion(int versorFila, int versorColumna) {
     
        this.versorFila = versorFila;
        this.versorColumna = versorColumna;
    }
    
    public Coordenada trasladar(Coordenada coordenada) {
        
        return Coordenada.con(coordenada.obtenerFila()    + versorFila,
                             coordenada.obtenerColumna() + versorColumna);
    }
}
