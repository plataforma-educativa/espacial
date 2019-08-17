import java.util.concurrent.TimeUnit;

class EscenarioEspacialFXTest {

    public static void main(String[] args) throws InterruptedException {

        EscenarioEspacial escenario = new EscenarioEspacial(10, 10);

        TimeUnit.SECONDS.sleep(5);

        escenario.colocarBaseEn(1, 1);

        Nave alfa = new Nave();
        alfa.despegar();
        alfa.avanzarAlNorte();
        alfa.avanzarAlNorte();

        escenario.colocarAsteroideEn(2, 4);

        Nave nave = new Nave();
        nave.despegar();
        nave.avanzarAlNorte();

        escenario.colocarAsteroideEn(4, 4);


        Nave nave2 = new Nave();
        nave2.despegar();
        nave2.avanzarAlOeste();
        nave2.avanzarAlOeste();

        escenario.colocarBaseEn(7, 8);

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