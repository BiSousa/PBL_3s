/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author fernando.cruz
 */

import Controllers.Fatorial;

public class SerieDeTaylor {
    Fatorial fatorial = new Fatorial();
    
    public double calcularSeno(double x, double erroMax){        
        x = reduzirAngulo(x);
        
        double seno = 0;
        
        for (int i = 0; i < erroMax; i++) {
            
            int expoente = 2 * i + 1; // Termos ímpares: x^1, x^3, x^5, etc.
            
            double termo = Math.pow(x, expoente) / fatorial.calculaFatorial(expoente); // Calcula x^expoente / expoente!
            
            // Alterna sinais
            if (i % 2 == 0) {
                seno += termo;  // Termos positivos
            } else {
                seno -= termo;  // Termos negativos
            }
        }
        
        return seno;
    }
    
     public double reduzirAngulo(double x) {
        x = x % (2 * Math.PI); 

        if (x > Math.PI) {
            x -= 2 * Math.PI;  
        } else if (x < -Math.PI) {
            x += 2 * Math.PI;   
        }

        if (x > Math.PI / 2) {
            x = Math.PI - x;
        } 
        else if (x < -Math.PI / 2) {
            x = -Math.PI - x;
        }

        return x;
    }
     
}
