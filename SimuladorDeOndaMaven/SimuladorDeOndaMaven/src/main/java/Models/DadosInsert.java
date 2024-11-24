/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.CallableStatement;

/**
 *
 * @author fernando.cruz
 */
public class DadosInsert {
    public void InserirDados(double frequencia, double comprimentoDaOnda, double tempoDaSimulacao, double erroMaximo, int usuarioID){
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        
        String sp_InserirDados = "{call sp_InserirDados(?,?,?,?,?)}";
        
        try (Connection conn = conexaoBanco.getConnection();
            CallableStatement cstmt  = conn.prepareCall(sp_InserirDados)) {
            // CallableStatement é uma biblioteca usada para chamar funções de Store Procedure
            
            // Definindo os parâmetros da instrução da Store Procedure
            cstmt.setDouble(1, frequencia);
            cstmt.setDouble(2, comprimentoDaOnda);
            cstmt.setDouble(3, tempoDaSimulacao);
            cstmt.setDouble(4, erroMaximo);
            cstmt.setInt(5, usuarioID);

            // Executando a procedure
            cstmt.execute();
            
            cstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir os dados: " + e.getMessage());
        }
    }
}
