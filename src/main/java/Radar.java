import espacial.Direccion;
import espacial.EspectroEspacial;
import espacial.NaveEspacial;

import java.util.EnumMap;
import java.util.Map;

/**
 * El Radar es la herramienta que provee una Nave para conocer el contexto
 * inmediato de la misma.
 *
 * @author Mariano Tugnarelli
 *
 */
public class Radar {

    private static final Map<EspectroEspacial, Espectro> ESPECTROS = new EnumMap(EspectroEspacial.class);

    static {

        ESPECTROS.put(EspectroEspacial.VACIO,       Espectro.VACIO);
        ESPECTROS.put(EspectroEspacial.ASTEROIDE,   Espectro.ASTEROIDE);
        ESPECTROS.put(EspectroEspacial.CONTENEDOR,  Espectro.CONTENEDOR);
        ESPECTROS.put(EspectroEspacial.BASE,        Espectro.BASE);
        ESPECTROS.put(EspectroEspacial.NAVE,        Espectro.NAVE);
        ESPECTROS.put(EspectroEspacial.DESCONOCIDO, Espectro.DESCONOCIDO);
    }

    private NaveEspacial pieza;

    protected Radar(NaveEspacial nave) {

        pieza = nave;
    }

    public Espectro escanearNorte() {

        return escanarEn(Direccion.NORTE);
    }

    public Espectro escanearSur() {

        return escanarEn(Direccion.SUR);
    }

    public Espectro escanearEste() {

        return escanarEn(Direccion.ESTE);
    }

    public Espectro escanearOeste() {

        return escanarEn(Direccion.OESTE);
    }

    private Espectro escanarEn(Direccion direccionElegida) {

        return interpretar(pieza.escanearEn(direccionElegida));
    }

    private Espectro interpretar(EspectroEspacial espectroEspacial) {

        return ESPECTROS.getOrDefault(espectroEspacial, Espectro.DESCONOCIDO);
    }
}
