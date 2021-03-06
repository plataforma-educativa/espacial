import espacial.test.Paso;

public class EjemploCargarCristal {

    public static void main(String[] args) {

        new BatallaEspacial();

        Nave nave = new Nave();
        nave.despegar();

        Monitor unMonitor = nave.obtenerMonitor();

        repetir(9, i -> nave.avanzarAlOeste());
        repetir(2, i -> nave.avanzarAlNorte());

        while (nave.obtenerRadar().escanearEste() == Espectro.ASTEROIDE) {
            nave.atacarAlEste();
        }
        nave.avanzarAlEste();

        nave.cargarDesdeEste(Sustancia.CRISTAL, 1);


        new BatallaEspacial();

        Nave nave2 = new Nave();
        nave2.despegar();

        unMonitor = nave2.obtenerMonitor();

        repetir(25, i -> nave2.avanzarAlOeste());
        repetir(2, i -> nave2.avanzarAlNorte());
        repetir(1, i -> nave2.avanzarAlOeste());
        repetir(7, i -> nave2.avanzarAlNorte());
        repetir(2, i -> nave2.avanzarAlEste());

        nave2.cargarDesdeNorte(Sustancia.METAL, 1);

    }

    static void repetir(int veces, Paso paso) {

        for (int i = 0; i < veces; i++) {

            paso.dar(i);
        }
    }
}
