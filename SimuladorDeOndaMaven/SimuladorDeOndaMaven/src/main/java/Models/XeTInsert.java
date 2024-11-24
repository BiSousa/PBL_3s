/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author fernando.cruz
 */
public class XeTInsert {
    public static void InserirDados(double x, double t){
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        
        String sp_InserirDadosXT = "{call sp_InserirDadosXT (?,?)}";
        
        try (Connection conn = conexaoBanco.getConnection();
            CallableStatement cstmt  = conn.prepareCall(sp_InserirDadosXT)) {
            // CallableStatement é uma biblioteca usada para chamar funções de Store Procedure
            
            // Definindo os parâmetros da instrução da Store Procedure
            cstmt.setDouble(1, x);
            cstmt.setDouble(2, t);
    
            // Executando a procedure
            cstmt.execute();
            
            cstmt.close();
            conn.close();
        }
        
        catch (SQLException e) {
            System.out.println("Erro ao inserir os dados: " + e.getMessage());
        }
    }
}
