/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * @author noelia
 */
public class SorpresaIRCARCEL extends Sorpresa{
    
    private Tablero tablero;
    
    SorpresaIRCARCEL(Tablero tablero) { // DONE! - CONSTRUIR SORPRESA QUE ENVIA A LA CARCEL
        super(-1, "¡Hacía la carcel vamos!");
        this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ // DONE!  
        Jugador jugador = todos.get(actual); //jugador
        if (super.jugadorCorrecto(actual, todos)) {
            super.informe(actual, todos);
            jugador.encarcelar(tablero.getCarcel());
        }
    }
    
    @Override 
    public String toString() {
        return super.toString() + "\n Tipo Sorpresa: IRCARCEL";
    }
}
