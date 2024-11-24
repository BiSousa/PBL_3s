/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import javax.swing.*;

/**
 *
 * @author fernando.cruz
 */
public class Sobre {
    public Sobre(){
        JFrame janela = new JFrame("Sobre");
        janela.setSize(720,480);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
        
        JLabel nomeFernando = new JLabel("Fernando Montanher da Cruz RA: 082230010");
        nomeFernando.setBounds(10, 10, 300, 30);
        JLabel nomeBeatriz = new JLabel("Beatriz RA: 082230010");
        nomeBeatriz.setBounds(10, 40, 300, 30);
        janela.add(nomeFernando);
        janela.add(nomeBeatriz);
        
        janela.setVisible(true);
    }
}
