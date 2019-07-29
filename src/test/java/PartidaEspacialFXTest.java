import espacial.partidas.PartidaEspacialFX;

import java.util.concurrent.TimeUnit;

class PartidaEspacialFXTest {

    public static void main(String[] args) throws InterruptedException {

        BatallaEspacial batalla = new BatallaEspacial();
        Nave nave = new Nave();
        nave.despegar();
        nave.avanzarAlSur();
        nave.avanzarAlSur();
        nave.avanzarAlSur();

        Nave nave2 = new Nave();
        nave2.despegar();
        nave2.avanzarAlSur();
        nave2.avanzarAlSur();
        nave2.avanzarAlSur();
        nave2.avanzarAlOeste();

        //new Nave().despegar();

        PartidaEspacialFX partidaEspacial = new PartidaEspacialFX(batalla.obtenerTablero());
        partidaEspacial.iniciar();

        Nave atacante = new Nave();
        atacante.despegar();
        TimeUnit.SECONDS.sleep(1);
        atacante.avanzarAlNorte();
        TimeUnit.SECONDS.sleep(5);
        atacante.atacarAlSur();
        TimeUnit.SECONDS.sleep(1);

        nave2.cargarDesdeOeste(Sustancia.ANTIMATERIA, 2);

        Radar radar2 = nave2.obtenerRadar();

        while (radar2.escanearOeste() == Espectro.CONTENEDOR) {

            nave2.atacarAlOeste();
        }

        nave2.avanzarAlEste();
        nave2.avanzarAlNorte();
        nave2.descargarEnNorte(Sustancia.ANTIMATERIA, 1);

        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < 20; i++) {

            nave2.atacarAlNorte();
        }
        TimeUnit.SECONDS.sleep(1);


        nave.avanzarAlEste();
        nave.avanzarAlEste();
        nave.avanzarAlEste();
        nave.avanzarAlEste();

        Radar radar = nave.obtenerRadar();

        while(radar.escanearNorte() == Espectro.ASTEROIDE) {
            nave.atacarAlNorte();
        }

        for (int i = 0; i < 10; i++) {

            TimeUnit.SECONDS.sleep(1);
            nave.avanzarAlEste();
            TimeUnit.SECONDS.sleep(1);
            nave.avanzarAlSur();
        }

    }

}