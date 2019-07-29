package espacial.utiles;

public interface Nombre {

    static Nombrando sera(String valor) {

        return new Nombrando(valor);
    }

    String obtener();

    String explicar();

    Nombre adicionando(String calificacion);
}
