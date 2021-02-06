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
public class SorpresaIRCASILLA extends Sorpresa{
    private Tablero tablero;
    
    SorpresaIRCASILLA (Tablero tablero, int valor, String texto) { // DONE! -  CONSTRUIR SORPRESA QUE ENVIA JUGADOR A OTRA CASILLA 
        super(valor, texto);
        this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ // DONE!
        Jugador jugador = todos.get(actual);        
        if (super.jugadorCorrecto(actual, todos)) {
            super.informe(actual, todos);
            int casillaActual = jugador.getNumCasillaActual();
            
            int tirada = tablero.calcularTirada(casillaActual, valor);
            int nuevaposicion = tablero.nuevaPosicion(casillaActual, tirada);
            
            jugador.moverACasilla(nuevaposicion);
            tablero.getCasilla(nuevaposicion).recibeJugador(valor, todos);    
        }
    }
    
    @Override 
    public String toString() {
        return super.toString() + "\n Tipo Sorpresa: IRCASILLA";
    }  
}
