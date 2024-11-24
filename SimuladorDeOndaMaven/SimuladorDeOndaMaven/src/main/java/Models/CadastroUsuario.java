/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.time.LocalDate;
import Utils.PasswordUtils;
import java.sql.ResultSet;

/**
 *
 * @author fernando.cruz
 */
public class CadastroUsuario {
    
    public String mensagem;

    public void InserirDados(String nomeUsuario, String senha, String email, LocalDate dataAtual){        
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        Date dataSql = Date.valueOf(dataAtual);
        String senhaCriptografada = PasswordUtils.hashPassword(senha);
        
        String sp_InserirDados = "{call sp_CriarContaUsuarios(?,?,?,?)}";
        
        try (Connection conn = conexaoBanco.getConnection();
            CallableStatement cstmt  = conn.prepareCall(sp_InserirDados)) {
            // CallableStatement é uma biblioteca usada para chamar funções de Store Procedure

            // Definindo os parâmetros da instrução da Store Procedure
            cstmt.setString(1, nomeUsuario);
            cstmt.setString(2, senhaCriptografada);
            cstmt.setString(3, email);
            cstmt.setDate(4, dataSql);

            // Executando a procedure
            cstmt.execute();
            
             // Executando a procedure de atualização
            ResultSet rs = cstmt.executeQuery();
            if(rs.next()){
                mensagem = rs.getString("Mensagem");
            }
            
            cstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir os dados: " + e.getMessage());
        }
    }
}
