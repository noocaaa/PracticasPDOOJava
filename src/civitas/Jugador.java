package civitas;

import GUI.Dado;
import java.util.ArrayList;

/**
 * @author noelia
 */
public class Jugador implements Comparable<Jugador> {
    protected static int  CasasMax=4;
    protected static int CasasPorHotel=4;
    protected static int HotelesMax=4;
    protected static float PasoPorSalida = 1000;
    protected static float PrecioLibertad = 200;
    private static float SaldoInicial = 7500; 
    
    private String nombre;
    private int numCasillaActual;
    protected boolean encarcelado;
    private boolean puedeComprar;
    private float saldo;   
    private SorpresaSALIRCARCEL salvoconducto; //tipo SALIRCARCEL
    private ArrayList<TituloPropiedad> propiedades;
    
    //EXAMEN
    
    private ArrayList<Integer> numeroDeCasillas;
    
    //FIN EXAMEN
    
    Jugador(String nombre){ // DONE!
        this.nombre = nombre;
        propiedades = new ArrayList();
        salvoconducto = null;
        saldo = SaldoInicial;
        puedeComprar = true;
        encarcelado = false;
        numCasillaActual = 0;
        
        //EXAMEN
        numeroDeCasillas = new ArrayList();
        //FIN EXAMEN
    }
    
    //constructor de copia
    protected Jugador(Jugador otro){ // DONE!
        if (this != otro) {
            this.nombre = otro.getNombre();
            this.propiedades = otro.getPropiedades();
            this.salvoconducto = otro.salvoconducto;
            this.saldo = otro.getSaldo();
            this.puedeComprar = otro.puedeComprar;
            this.encarcelado = otro.isEncarcelado();
            this.numCasillaActual = otro.getNumCasillaActual();
            //EXAMEN
            this.numeroDeCasillas = otro.getNumeroCasilla();
            //FIN EXAMEN
        }
    }
    
    boolean cancelarHipoteca(int ip) { //DONE!
        boolean result = false; //1
        if (this.isEncarcelado()) { 
            return result; //2
        }
        
        if (this.existeLaPropiedad(ip)) { 
            TituloPropiedad propiedad = propiedades.get(ip); //3
            float cantidad = propiedad.getImporteCancelarHipoteca(); //4
            boolean puedoGastar = this.puedoGastar(cantidad); //5
            if (puedoGastar) {
                result = propiedad.cancelarHipoteca(this); //6
                                
                if (result) { //7
                    Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " cancela la hipoteca de la propiedad " + propiedades.get(ip));
                }                 
            }
        }
       
        return result;
    }
    
    int cantidadCasasHoteles(){ // DONE!
        int num = 0;
        for (int i=0; i < propiedades.size(); i++) {
            num += propiedades.get(i).cantidadCasasHoteles();
        }
        return num;
    }
    
    @Override
    public int compareTo(Jugador otro){ // DONE!
        int dev; 
        if (Float.compare(saldo, otro.getSaldo()) == 0) {
            dev = 0; // si son iguales los saldos
        } else if (Float.compare(saldo, otro.getSaldo()) > 0) {
            dev = 1; // si el saldo del this es mayor que el del otro
        } else {
            dev = 2; // si el saldo del this es menor que el del otro
        }
        return dev;
    }
    
    boolean comprar(TituloPropiedad titulo){ // DONE!
        boolean result = false;        
        if (isEncarcelado()){
            return result;
        }
        
        if (puedeComprar) { 
            float precio = titulo.getPrecioCompra();
            if (puedoGastar(precio)) {
                result = titulo.comprar(this);
                if (result) {
                    propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " compra la propiedad " + titulo.toString());
                }
                puedeComprar = false;
            }
        }
        
        return result;
    }
    
    boolean construirCasa(int ip){ // DONE!
        boolean result=false, puedoEdificarCasa = false;
        
        if(isEncarcelado()){
            return result;
        } else {
            boolean existe = existeLaPropiedad(ip);
            if (existe) {
                TituloPropiedad propiedad = propiedades.get(ip);
                puedoEdificarCasa = puedoEdificarCasa(propiedad);
                float precio = propiedad.getPrecioEdificar();
                
                if(puedoGastar(precio) && propiedad.getNumCasas()<getCasasMax()){
                    puedoEdificarCasa = true;
                }

                if (puedoEdificarCasa) {
                    result = propiedad.construirCasa(this);
                    if (result) {
                        Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " construye casa en la propiedad " + propiedades.get(ip));                     
                    }
                }
            }
        }
        
        
        return result;
    }
    
    boolean construirHotel(int ip) { // DONE!
        boolean result = false;
        
        if (isEncarcelado()){
            return result;
        }
        
        if (existeLaPropiedad(ip)) {
            TituloPropiedad propiedad = propiedades.get(ip); //1
            boolean puedoEdificarHotel = this.puedoEdificarHotel(propiedad); //2
            float precio = propiedad.getPrecioEdificar(); // 2.2
            
            if (puedoGastar(precio)) {
                if (propiedad.getNumHoteles() < getHotelesMax()){
                    if (propiedad.getNumCasas() >= getCasasPorHotel()) {
                        puedoEdificarHotel = true;
                    }
                }
            }
            
            if (puedoEdificarHotel) {
                result = propiedad.construirHotel(this);
                int casasPorHotel = this.getCasasPorHotel();
                propiedad.derruirCasas(casasPorHotel, this);
                Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " construye hotel en la propiedad " + propiedades.get(ip));
            }
            
        }
        
        return result;
        
    }
    
    protected boolean debeSerEncarcelado(){ // DONE!
        boolean devuelve;
        if (isEncarcelado()) {
            devuelve = false;
        } else {
            if (!this.tieneSalvoconducto()) {
                devuelve = true;
            } else {
                String texto = "El jugador " + this.getNombre() + " se libra de la carcel.";
                this.perderSalvoconducto();
                Diario.getInstance().ocurreEvento(texto);
                devuelve = false;            
            }
        }
        return devuelve;
    }
    
    boolean enBancarrota() { // DONE!
        return saldo < 0;
    }
    
    boolean encarcelar(int numCasillaCarcel){ // DONE!
        if (this.debeSerEncarcelado()) {
            this.moverACasilla(numCasillaCarcel);
            encarcelado = true;
            Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() +  " ha sido encarcelado directo a la carcel");
        }
        return encarcelado;
    }
    
    private boolean existeLaPropiedad(int ip) { // DONE!
        boolean estado = false;
        for (int i=0; i < propiedades.size(); i++) {
            if (propiedades.get(i) == propiedades.get(ip)) {
                estado = true;
            }
        }
        return estado;
    }
    
    //MÉTODO AÑADIDO PARA GUI
    public boolean esEspeculador() {
        return false;
    }
    
    protected int getCasasMax(){ // DONE!
        return CasasMax;
    }
    
    int getCasasPorHotel(){ // DONE!
        return CasasPorHotel;
    }
    
    protected int getHotelesMax(){ // DONE!
        return HotelesMax;
    }
    
    public String getNombre(){ // DONE!
        return nombre;
    }
    
    int getNumCasillaActual(){ // DONE!
        return numCasillaActual;
    }
    
    private float getPrecioLibertad(){ // DONE!
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida(){ // DONE!
        return PasoPorSalida;
    }
       
    //HE MODIFICADO LA VISIBILIDAD DE PROTECTED A VISIBILIDAD PUBLICA DEBIDO AL METODO GESTIONAR EN VISTATEXTUAL
    public ArrayList<TituloPropiedad> getPropiedades() { // DONE!
        return propiedades;
    }
    
    boolean getPuedeComprar(){ // DONE!
        return puedeComprar;
    }
    
    public float getSaldo(){ // DONE!
        return saldo;
    }
    
    boolean hipotecar (int ip) { // DONE!
        boolean result = false;
        if (isEncarcelado()) {
            return result;
        }
        
        if (existeLaPropiedad(ip)) {
            TituloPropiedad propiedad = propiedades.get(ip);
            result = propiedad.hipotecar(this);          
        }
        
        if (result) {
            Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " hipoteca la propiedad " + propiedades.get(ip));
        }
        
        return result;
    }
    
    public boolean isEncarcelado(){ // DONE!
        return encarcelado;
    }
    
    boolean modificarSaldo(float cantidad) { // DONE!
        saldo += cantidad;
        String texto = "Se ha modificado el saldo del Jugador " + this.getNombre() + ". Por tanto, el saldo actual de este jugador es de " + saldo ;
        Diario.getInstance().ocurreEvento(texto);
        return true;
    }
    
    //EXAMEN
    
    public ArrayList<Integer> getNumeroCasilla() {
        return numeroDeCasillas;
    }
    
    //FIN EXAMEN
    
    boolean moverACasilla(int numCasilla) { // DONE!
        boolean devuelve;
        if (isEncarcelado()){
            devuelve = false;
        } else {
            numCasillaActual = numCasilla;
            
            //EXAMEN
            numeroDeCasillas.add(numCasillaActual);
            //FIN EXAMEN
            
            puedeComprar = false;
            String texto = "Se ha movido el Jugador " +  this.getNombre() + " a la casilla.";
            Diario.getInstance().ocurreEvento(texto);
            devuelve = true;
        }
        return devuelve;
    }
    
    boolean obtenerSalvoconducto(SorpresaSALIRCARCEL sorpresa) { // DONE!
        boolean dev;
        if (!isEncarcelado()) {
            dev = true;
            salvoconducto = sorpresa;
        } else {
            dev = false;
        }
        return dev;
    }
    
    boolean paga (float cantidad) { // DONE!
        float num = (float)(cantidad * -1);
        return this.modificarSaldo(num);
    }
    
    boolean pagaAlquiler(float cantidad) { // DONE!
        boolean dev;
        if (isEncarcelado()){
            dev = false;
        } else {
            dev = paga(cantidad);
        }
        return dev;    }
    
    boolean pagaImpuesto(float cantidad) { // DONE!
        boolean dev;
        if (isEncarcelado()){
            dev = false;
        } else {
            dev = paga(cantidad);
        }
        return dev;
    }
    
    boolean pasaPorSalida(){ // DONE!
        modificarSaldo(getPremioPasoSalida());
        String texto = "El jugador " + this.getNombre() + " ha pasado por la salida. Y su saldo se ha visto incrementado. Ahora tiene un saldo de " + this.getSaldo();
        Diario.getInstance().ocurreEvento(texto);
        return true;
    }
    
    protected void perderSalvoconducto(){ // DONE!
        salvoconducto.usada();
        salvoconducto = null;
    }
    
    boolean puedeComprarCasilla(){ // DONE!
        if (isEncarcelado()){
            puedeComprar = false;
        } else {
            puedeComprar = true;
        }
        return puedeComprar; 
    }
    
    private boolean puedeSalirCarcelPagando(){ // DONE!
        return saldo >= PrecioLibertad;
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad) { // DONE!
        return saldo >= propiedad.getPrecioEdificar() && propiedad.getNumCasas() < CasasMax;
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad) { // DONE!
        return saldo >= propiedad.getPrecioEdificar() && propiedad.getNumCasas() >= CasasPorHotel && propiedad.getNumHoteles() < HotelesMax;
    }
    
    protected boolean puedoGastar(float precio) { // DONE!
        boolean dev;
        if (isEncarcelado()) {
            dev = false;
        } else {
            if (saldo >= precio) {
                dev = true;
            } else {
                dev = false;
            }
        }
        return dev;
    }
    
    boolean recibe(float cantidad) { // DONE!
        boolean dev;
        if (isEncarcelado()) {
            dev = false;
        } else {
            dev = modificarSaldo(cantidad);
        }
        return dev;
    }
    
    boolean salirCarcelPagando(){ // DONE!
        boolean dev;
        if (isEncarcelado() && puedeSalirCarcelPagando()) {
            paga(PrecioLibertad);
            encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " ya no esta encarcelado.");
            dev = true;
        } else {
            dev = false;
        }
        return dev;
    }
    
    boolean salirCarcelTirando() { // DONE!
        boolean dev;
        if (Dado.getInstance().salgoDeLaCarcel()) {
            encarcelado = false;
            dev = true;
            Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " ha salido de la carcel.");
        } else {
            dev = false;
        }
        return false;
    }
    
    boolean tieneAlgoQueGestionar(){ // DONE!
        return !propiedades.isEmpty();
    }
    
    boolean tieneSalvoconducto() { // DONE!
        return salvoconducto != null;
    }
    
    @Override
    public String toString(){ // DONE!
        return "Nombre del jugador: " + this.nombre +
                "\n Numero de Casilla Actual: " + this.numCasillaActual + 
                "\n Puede Comprar: " + this.puedeComprar +
                "\n Saldo Actual: " + this.saldo +
                "\n Propiedades: " + this.propiedades.size() +
                "\n Salvo Conducto: " + this.salvoconducto +
                "\n Encarcelado: " + this.encarcelado;
    }
    
    boolean vender (int ip) { // DONE!
        boolean dev = false;
        if (!isEncarcelado()) {
            if (existeLaPropiedad(ip)) {
                boolean c = propiedades.get(ip).vender(this);
                if (c) {
                   dev = true;
                   Diario.getInstance().ocurreEvento("El jugador " + this.getNombre() + " ha vendido la propiedad " + propiedades.get(ip));
                   propiedades.remove(ip);
                }
            }
        }
        return dev;
    }
    
}
