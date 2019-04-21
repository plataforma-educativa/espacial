package espacial;


public enum Direccion {
    
    NORTE() {
        public Coordenada trasladar(Coordenada coordenada) {
            
            return Coordenada.en(coordenada.obtenerFila() + 1, coordenada.obtenerColumna());
        }
    }, 
    
    SUR() {

        @Override
        public Coordenada trasladar(Coordenada coordenada) {
    
            return Coordenada.en(coordenada.obtenerFila() - 1, coordenada.obtenerColumna());
        }
        
    };

    public abstract Coordenada trasladar(Coordenada coordenada);
}
