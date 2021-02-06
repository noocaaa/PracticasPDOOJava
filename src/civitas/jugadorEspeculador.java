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
public class jugadorEspeculador extends Jugador{
    
    private static int FactorEspeculador = 2;
    private float fianza;
    
    
    jugadorEspeculador(Jugador player, float fianza){
        super(player);
        this.fianza = fianza;
        
        for(TituloPropiedad p : player.getPropiedades())
            p.actualizarPropietarioPorConversion(player);
    }
    
    @Override
    public boolean esEspeculador() {
        return true;
    }
    
    @Override
    protected int getCasasMax(){
        return super.getCasasMax()*FactorEspeculador;
    }    
        
    @Override
    protected int getHotelesMax(){
        return super.getHotelesMax()*FactorEspeculador;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n \nEl jugador es un JugadorEspeculador.";
    }
    
    @Override   
    boolean pagaImpuesto(float cantidad) { // DONE!
        boolean dev;
        if (isEncarcelado()){
            dev = false;
        } else {
            dev = paga(cantidad/FactorEspeculador);
        }
        return dev;
    }
    
    @Override
    protected boolean debeSerEncarcelado(){ // DONE!
        boolean devuelve;
        
        if (super.isEncarcelado()) {
            devuelve = false;
        } else {
            if (!super.tieneSalvoconducto()) {                
                if (super.puedoGastar(fianza)) {
                    devuelve = false;
                    super.paga(fianza);
                    String texto = "El jugador " + super.getNombre() + " se libra de la carcel pagando la fianza (es un jugadorEspeculador).";
                    Diario.getInstance().ocurreEvento(texto);
                } else {
                    devuelve = true;
                }
                
            } else {
                String texto = "El jugador " + super.getNombre() + " se libra de la carcel debido al salvoconducto (es un jugadorEspeculador).";
                super.perderSalvoconducto();
                Diario.getInstance().ocurreEvento(texto);
                devuelve = false;            
            }
        }
        
        return devuelve;
    }
}
