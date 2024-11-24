/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author fernando.cruz
 */
public class Fatorial {
    public long calculaFatorial(int n){
        if (n == 0){
            return 1;
        }
        
        int fatorial = 1;
        for(int i = 1; i <= n; i++){
            fatorial *= i;
        }
        return fatorial;
    }
}
