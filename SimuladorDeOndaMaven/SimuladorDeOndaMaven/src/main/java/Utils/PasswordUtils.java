/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Guilherme Pexirile
 */
public class PasswordUtils {
    // Método para criptografar a senha
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    
    // Método para verificar se a senha informada corresponde ao hash armazenado
    public static boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }


}
