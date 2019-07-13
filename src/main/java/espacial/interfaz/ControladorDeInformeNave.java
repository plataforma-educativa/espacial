package espacial.interfaz;

import espacial.Pieza;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorDeInformeNave extends ControladorDeInformePieza {

    @FXML
    private Label escudos;
    
    @FXML
    private Label carga;
    
    @FXML
    private Label arsenal;

    public ControladorDeInformeNave(Pieza unaPieza) {

        super(unaPieza);
    }

    @Override
    protected void completar() {

        panel.setText(this.pieza.describir());
        
        StringBuilder valorEscudo = new StringBuilder();
        valorEscudo.append(pieza.obtenerPuntos())
                   .append("%");
        
        escudos.setText(valorEscudo.toString());
    }
}
