/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Controllers.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fernando.cruz
 */
public class VerificarUsuario {
    public int VerificarUsuarioID(String nomeDoUsuario){
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        String usuarioId = "";
        
        String sp_InserirDados = "{call sp_VerificarUsuarioID(?)}";
        
        try (Connection conn = conexaoBanco.getConnection();
            CallableStatement cstmt  = conn.prepareCall(sp_InserirDados);) {
            // CallableStatement é uma biblioteca usada para chamar funções de Store Procedure
            
            // Definindo os parâmetros da instrução da Store Procedure
            cstmt.setString(1, nomeDoUsuario);
            
            ResultSet rs = cstmt.executeQuery();
            
            if(rs.next()){
                usuarioId = rs.getString("UsuarioID");
                
                return Integer.parseInt(usuarioId);
            }

            // Executando a procedure
            cstmt.execute();
            
            cstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir os dados: " + e.getMessage());
        }
        
        return 100;
    }
}
