/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author fernando.cruz
 */

public class EquacaoDaOnda {
   
    public double ValorDePI = Math.PI;
    public double ErroMaximo = 10;
    //public double ValorDeT;
    
    SerieDeTaylor seno = new SerieDeTaylor();
   
    double y = 0;
    
    public double Calcular(double valorX, double t, double f, double c){
        
        double conta = 2 * ValorDePI * (f  * t - valorX / c);
        y = seno.calcularSeno(conta, ErroMaximo);

        return y;   
    }
    
}
