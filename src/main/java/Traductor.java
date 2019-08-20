import espacial.EspectroEspacial;
import espacial.SustanciaEspacial;

import java.util.EnumMap;
import java.util.Map;

interface Traductor<E,S> {

    Traductor<Sustancia, SustanciaEspacial> DE_SUSTANCIAS = new TraductorDeSustancias();

    Traductor<EspectroEspacial, Espectro> DE_ESPECTROS = new TraductorDeEspectros();

    S interpretar(E valor);

    class TraductorDeSustancias implements Traductor<Sustancia, SustanciaEspacial> {

        private final Map<Sustancia, SustanciaEspacial> sustancias = new EnumMap<>(Sustancia.class);

        private TraductorDeSustancias() {

            sustancias.put(Sustancia.ANTIMATERIA, SustanciaEspacial.ANTIMATERIA);
            sustancias.put(Sustancia.METAL, SustanciaEspacial.METAL);
            sustancias.put(Sustancia.CRISTAL, SustanciaEspacial.CRISTAL);
        }

        @Override
        public SustanciaEspacial interpretar(Sustancia valor) {

            return sustancias.getOrDefault(valor, SustanciaEspacial.ANTIMATERIA);
        }
    }

    class TraductorDeEspectros implements Traductor<EspectroEspacial, Espectro> {

        private final Map<EspectroEspacial, Espectro> espectros = new EnumMap<>(EspectroEspacial.class);

        private TraductorDeEspectros() {

            espectros.put(EspectroEspacial.VACIO,       Espectro.VACIO);
            espectros.put(EspectroEspacial.ASTEROIDE,   Espectro.ASTEROIDE);
            espectros.put(EspectroEspacial.CONTENEDOR,  Espectro.CONTENEDOR);
            espectros.put(EspectroEspacial.BASE,        Espectro.BASE);
            espectros.put(EspectroEspacial.NAVE,        Espectro.NAVE);
            espectros.put(EspectroEspacial.DESCONOCIDO, Espectro.DESCONOCIDO);
        }

        @Override
        public Espectro interpretar(EspectroEspacial valor) {

            return espectros.getOrDefault(valor, Espectro.DESCONOCIDO);
        }
    }
}
