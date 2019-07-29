package espacial.partidas;

import espacial.datos.NombresDePersonalidades;
import espacial.datos.SubfijosOrdinales;
import espacial.utiles.Aleatorio;
import espacial.utiles.Nombre;

public class Nomenclador {

    private final Aleatorio<Nombre> nombreAleatorio = Aleatorio.consumiendo(NombresDePersonalidades.listar());
    private final Aleatorio<String> sufijoAleatorio = Aleatorio.enLista(SubfijosOrdinales.listar());

    public Nombre nombrarNave() {

        return nombreAleatorio.obtener().adicionando(sufijoAleatorio.obtener());
    }
}
