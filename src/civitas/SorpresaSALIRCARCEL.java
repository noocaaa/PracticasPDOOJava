/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author noelia
 */
public class SorpresaSALIRCARCEL extends Sorpresa {
    
    private MazoSorpresas mazo;
    
    SorpresaSALIRCARCEL(MazoSorpresas mazo){ // DONSTRUIR SORPRESA EVITAR LA CARCEL
        super(-1, "Evitar la carcel");
        this.mazo = mazo;
    }
    
    void usada(){ // DONE!
        mazo.habilitarCartaEspecial(this);
    }
    
    void salirDelMazo(){ // DONE!
        mazo.inhabilitarCartaEspecial(this);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ // DONE!
        Jugador jugador = todos.get(actual);
        boolean continuar = true;
        
        if (super.jugadorCorrecto(actual, todos)) {
            super.informe(actual,todos);
            
            for(int i=0; i < todos.size() && continuar; i++){
                if(jugador.tieneSalvoconducto()){
                    continuar = false;
                }
            }
            
            if (continuar) {
                jugador.obtenerSalvoconducto(this);
                salirDelMazo();
            }
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "\nTipo Sorpresa: SALIRCARCEL";
    }
    
}
