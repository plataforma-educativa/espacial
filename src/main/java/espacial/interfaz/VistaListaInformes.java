package espacial.interfaz;

import espacial.BaseEspacial;
import espacial.NaveEspacial;
import espacial.Pieza;
import espacial.Visitante;
import espacial.utiles.AccionSobre;
import espacial.utiles.Proveedor;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class VistaListaInformes implements Visitante {

    private final Pane panel;
    private final AccionSobre<VistaInforme> alAgregar;
    private final Map<Pieza, VistaInforme> vistaInformePorPieza = new HashMap<>();

    public VistaListaInformes(Pane panelConInformes, AccionSobre<VistaInforme> ejecutarAlAgregarInforme) {

        panel = panelConInformes;
        alAgregar = ejecutarAlAgregarInforme;
    }

    @Override
    public void siEsNave(NaveEspacial nave) {

        obtenerVista(nave, () -> new VistaInformeNave(panel, nave)).seleccionar();
    }

    @Override
    public void siEsBase(BaseEspacial pieza) {

        obtenerVista(pieza, () -> new VistaInformeBase(panel, pieza)).seleccionar();
    }

    public void mostrarInformePara(Pieza unaPieza) {

        unaPieza.aceptar(this);
    }

    private VistaInforme obtenerVista(Pieza pieza, Proveedor<VistaInforme> proveedorDeVista) {

        VistaInforme vista = vistaInformePorPieza.computeIfAbsent(pieza, p -> proveedorDeVista.obtener());

        alAgregar.ejecutar(vista);

        return vista;
    }
}
