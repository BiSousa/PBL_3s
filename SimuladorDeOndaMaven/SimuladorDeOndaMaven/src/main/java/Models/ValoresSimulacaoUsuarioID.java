/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fernando.cruz
 */
public class ValoresSimulacaoUsuarioID {
    
    public double FrequenciaValor = 0;
    public double ComprimentoDaOndaValor = 0;
    public double TempoDaSimulacaoValor = 0;
    public double ErroMaximoValor = 0;
    
    public ValoresSimulacaoUsuarioID(int UsuarioID){
        // Utilização do try-catch para realizar a conexão e fazer a consulta no banco de dados
        ConexaoBanco conexao = new ConexaoBanco();
            
        // Comando sql que está chamando uma Storeged Procedure dentro do banco de dados
        String sp_UltimaSimulacaoUsuarioID = "{call sp_ValorDaUltimaSimulacaoPorUsuarioID(?)}";
    
        try(Connection conn = conexao.getConnection();
            CallableStatement cstmt = conn.prepareCall(sp_UltimaSimulacaoUsuarioID);){
            
            cstmt.setInt(1, UsuarioID);
            
            ResultSet rs = cstmt.executeQuery();
            
            if (rs.next()) { // Verifica se há uma linha de dados no ResultSet
                FrequenciaValor = rs.getFloat("Frequencia");
                ComprimentoDaOndaValor = rs.getFloat("ComprimentoDaOnda");
                TempoDaSimulacaoValor = rs.getFloat("TempoDaSimulacao");
                ErroMaximoValor = rs.getFloat("ErroMaximo");
            } else {
                System.out.println("Nenhum dado encontrado para o UsuarioID fornecido.");
            }
            
            rs.close(); // Fecha a sessão de consulta sql no banco
            cstmt.close(); // Fecha statement
            conn.close(); // Fecha a conexão
        } catch (SQLException e) {
            e.printStackTrace(); // Caso seja gerada algum erro irá aparecer aqui
        }
    }
}
