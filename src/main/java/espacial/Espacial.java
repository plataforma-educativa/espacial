package espacial;

import espacial.interfaz.InterfazFX;
import espacial.utiles.Proveedor;
import espacial.utiles.Referencia;

public abstract class Espacial {

    private static Referencia<Interfaz> interfaz = Referencia.con(InterfazFX::new);

    public static void crearInterfaz(Partida partida) {

        interfaz.obtener().mostrar(partida);
    }

    public static void usar(Proveedor<Interfaz> proveedor) {

        interfaz.siEsNuloAlObtener(proveedor);
    }
}
