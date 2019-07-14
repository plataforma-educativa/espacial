package espacial.partidas;

import espacial.datos.NombresDePersonalidades;
import espacial.utiles.Aleatorio;
import espacial.utiles.Nombre;

public class Nomenclador {

    private final Aleatorio<Nombre> nombreAleatorio = Aleatorio.consumiendo(NombresDePersonalidades.listar());

    public Nombre nombrarNave() {

        return nombreAleatorio.obtener();
    }
}
