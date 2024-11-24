/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import Utils.PasswordUtils;


/**
 *
 * @author fernando.cruz
 */
public class ConfirmarLogin {
        
    public boolean Login(String nomeUsuario, String senha){        
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        
        String sp_ConfirmarLogin = "{call sp_ConfirmarLogin(?)}";
        
        try (Connection conn = conexaoBanco.getConnection();
            CallableStatement cstmt  = conn.prepareCall(sp_ConfirmarLogin)) {
            // CallableStatement é uma biblioteca usada para chamar funções de Store Procedure

            // Definindo os parâmetros da instrução da Store Procedure
            cstmt.setString(1, nomeUsuario);
            
            // Executando a procedure
            ResultSet rs = cstmt.executeQuery();
             
            if(rs.next()){
                String senhaCriptografada = rs.getString("Senha");
              
                if(PasswordUtils.checkPassword(senha, senhaCriptografada)){
                    //System.out.println("Autenticacao com sucesso!");
                    return true;
                }else {
                    //System.out.println("Falha ao acessar a conta!");
                    return false;
                }
            }else {
                //System.out.println("Usuário não encontrado.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }        
    }
}
