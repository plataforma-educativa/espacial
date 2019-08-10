import espacial.partidas.PartidaEspacialFX;

import java.util.concurrent.TimeUnit;

class PartidaEspacialFXTest {

    public static void main(String[] args) throws InterruptedException {

        BatallaEspacial batalla = new BatallaEspacial();

        PartidaEspacialFX partidaEspacial = new PartidaEspacialFX(batalla.obtenerTablero());
        partidaEspacial.iniciar();

        TimeUnit.SECONDS.sleep(5);

        Nave alfa = new Nave();
        alfa.despegar();
        alfa.avanzarAlNorte();
        alfa.avanzarAlNorte();
        alfa.avanzarAlOeste();
        alfa.avanzarAlSur();

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

        Nave atacante = new Nave();
        atacante.despegar();
        atacante.avanzarAlNorte();
        atacante.atacarAlSur();

        nave2.cargarDesdeOeste(Sustancia.ANTIMATERIA, 2);

        Radar radar2 = nave2.obtenerRadar();

        while (radar2.escanearOeste() == Espectro.CONTENEDOR) {

            nave2.atacarAlOeste();
        }

        nave2.avanzarAlEste();
        nave2.avanzarAlNorte();
        nave2.descargarEnNorte(Sustancia.ANTIMATERIA, 1);

        while(radar2.escanearNorte() == Espectro.BASE) {

            nave2.atacarAlNorte();
        }


        nave.avanzarAlEste();
        nave.avanzarAlEste();
        nave.avanzarAlEste();
        nave.avanzarAlEste();

        Radar radar = nave.obtenerRadar();

        while(radar.escanearNorte() == Espectro.ASTEROIDE) {
            nave.atacarAlNorte();
        }

        for (int i = 0; i < 10; i++) {

            nave.avanzarAlEste();
            nave.avanzarAlSur();
        }

    }

}