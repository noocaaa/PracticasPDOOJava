/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 * @author noelia
 */
public class TituloPropiedad { // DONE!
        
    private float alquilerBase;
    private static float factorInteresesHipoteca = 1.1f;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    
    private Jugador propietario;    
    
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe) { // DONE!
        nombre = nom;
        alquilerBase = ab;
        factorRevalorizacion = fr;
        hipotecaBase = hb;
        precioCompra = pc;
        precioEdificar = pe;
     
        hipotecado = false;
        numCasas = numHoteles = 0; 
        propietario = null;
    }
    
    void actualizarPropietarioPorConversion(Jugador jugador){ // DONE!
        propietario = jugador;
    }
    
    boolean cancelarHipoteca(Jugador jugador){ // DONE!
        boolean result = false;
        if (this.getHipotecado()) {
            if (this.esEsteElPropietario(jugador)) {
                propietario.paga(this.getImporteCancelarHipoteca());
                hipotecado = false;
                result = true;
            }
        }
        return result;
    }
    
    int cantidadCasasHoteles(){ // DONE!
        return getNumCasas() + getNumHoteles();
    }
    
    boolean comprar(Jugador jugador){ // DONE!
        boolean result = false;
        if (!tienePropietario()) {
            actualizarPropietarioPorConversion(jugador);
            result = true;
            propietario.paga(getPrecioCompra());
        }
        return result;
    }
    
    boolean construirCasa(Jugador jugador){ // DONE!
        boolean result = false;
        if(esEsteElPropietario(jugador) && numCasas <= 4){
            propietario.paga(precioEdificar);
            numCasas += 1;
            result = true;
        }
        return result;
    }
    
    boolean construirHotel(Jugador jugador){ // DONE!
        boolean result = false;
        if (esEsteElPropietario(jugador) && numHoteles <=4 && numCasas >= 4){
            propietario.paga(precioEdificar);
            numHoteles = numHoteles + 1;
            result = true;
        }
        return result;
    }
    
    boolean derruirCasas(int n, Jugador jugador){ // DONE!
        boolean estado;
        if (esEsteElPropietario(jugador) && getNumCasas() >= n){
            numCasas = numCasas - n;
            estado = true;
        } else {
            estado = false;
        }
        
        return estado;
    }
    
    private boolean esEsteElPropietario(Jugador jugador){ // DONE!
        return jugador == propietario;
    }
    
    public boolean getHipotecado(){ // DONE!
        return hipotecado;
    }
    
    float getImporteCancelarHipoteca(){ // DONE!
        return getImporteHipoteca()*factorInteresesHipoteca;
    }
    
    private float getImporteHipoteca() { // DONE!
        float resultado = (float)(hipotecaBase*(1 + (getNumCasas() * 0.5) + (getNumHoteles() * 2.5)));
        return resultado;
    }
    
    //HE MODIFICADO LA VISIBILIDAD DE CLASE A VISIBILIDAD PUBLICA, DEBIDO AL GESTIONAR DEL FICHERO VISTA TEXTUAL
    public String getNombre(){ // DONE!
        return nombre;
    }
    
    public int getNumCasas(){ // DONE!
        return numCasas;
    }
    
    public int getNumHoteles(){ // DONE!
        return numHoteles;
    }
    
    float getPrecioAlquiler(){ // DONE!
        float precio = (float)(alquilerBase*(1+(getNumCasas()*0.5)+(getNumHoteles()*2.5)));
        if (getHipotecado() || propietarioEncarcelado()) {
            precio = 0;
        }
        return precio;
    }
    
    float getPrecioCompra(){ // DONE!
        return precioCompra;
    }
    
    float getPrecioEdificar(){ // DONE!
        return precioEdificar;
    }
    
    private float getPrecioVenta(){ // DONE!
        return getPrecioCompra() + (getNumCasas()+5*getNumHoteles())*getPrecioEdificar()*factorRevalorizacion;
    }
    
    Jugador getPropietario() {// DONE!
        return propietario;
    }
    
    boolean hipotecar(Jugador jugador){ // DONE!
        boolean salida = false;
        if (!hipotecado && esEsteElPropietario(jugador)){ //6.1
            propietario.recibe(getImporteHipoteca());
            hipotecado = true;
            salida = true;
        }
        return salida;
    }
    
    private boolean propietarioEncarcelado(){ // DONE!
        return propietario.isEncarcelado() ;
    }
    
    boolean tienePropietario(){ // DONE!
        return propietario != null;
    }
    
    @Override
    public String toString(){ // DONE!
        return "Las caracteristicas del titulo de Propiedad son las siguientes: \n" + 
                "Nombre: " + nombre +
                "\n Factor Intereses Hipoteca: " + factorInteresesHipoteca +
                "\n Factor Revalorización: " + factorRevalorizacion+
                "\n Hipoteca Base: " + hipotecaBase +
                "\n ¿Esta hipotecado el titulo?: " + hipotecado +
                "\n Alquiler Base: " + alquilerBase +
                "\n Numero de casas: " + numCasas +
                "\n Numero de hoteles: " + numHoteles + 
                "\n Precio de compra: " + precioCompra +
                "\n Propietario: " + propietario + 
                "\n Precio de edificar:" + precioEdificar + "\n";
    }
    
    void tramitarAlquiler(Jugador jugador){ // DONE!
        if (tienePropietario()) {
            if (!esEsteElPropietario(jugador)) {
                float precio = getPrecioAlquiler();
                jugador.pagaAlquiler(precio);
                propietario.recibe(precio);
            }
        }
    }
    
    boolean vender(Jugador jugador){ // DONE!
        boolean estado;
        if (esEsteElPropietario(jugador) && !getHipotecado()) {
            propietario.recibe(getPrecioVenta());
            actualizarPropietarioPorConversion(null);
            numCasas = numHoteles = 0;
            estado = true;
        } else {
            estado = false;
        }
        return estado;
    }
    
    
}
