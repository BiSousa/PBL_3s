/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Models.*;
import javax.swing.JTextField;

/**
 *
 * @author fernando.cruz
 */
public class VerificarCampoVazio {
    public static boolean CampoVazio(JTextField... campos){
        for(JTextField campo : campos) {
            if (campo.getText().trim().isEmpty()) {
                campo.requestFocus();
                return true; // Retorna true se algum campo estiver vazio
            }
        }
        return false;
    }
}
