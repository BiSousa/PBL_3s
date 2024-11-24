/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Utils.PasswordUtils;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author fernando.cruz
 */
public class TrocarSenha {
    public boolean TrocarSenha(String nomeUsuario, String senhaAtual, String senhaModificada){        
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        // Primeiro, recuperar a senha criptografada atual do banco de dados
        String senhaCriptografadaAtual = getHashedPasswordFromDB(nomeUsuario);

        if(senhaCriptografadaAtual == null || !PasswordUtils.checkPassword(senhaAtual, senhaCriptografadaAtual)){
            System.out.println("Senha atual incorreta.");
            return false;
        }

        // Se a senha atual está correta, criptografar a nova senha e atualizar no banco
        String senhaAlteradaCriptografada = PasswordUtils.hashPassword(senhaModificada);
        
        // Comando sql que está chamando uma Storeged Procedure dentro do banco de dados
        String sp_TrocarSenha = "{call sp_TrocarSenha(?,?)}";

        try (Connection conn = conexaoBanco.getConnection();
            CallableStatement cstmt  = conn.prepareCall(sp_TrocarSenha)) {
            
            // CallableStatement para chamar funções de Stored Procedure
            cstmt.setString(1, nomeUsuario);
            cstmt.setString(2, senhaAlteradaCriptografada);

            // Executando a procedure de atualização
            ResultSet rs = cstmt.executeQuery();
            if(rs.next()){
                String mensagem = rs.getString("Mensagem");
                System.out.println(mensagem);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir os dados: " + e.getMessage());
            return false;
        }
    }

    private String getHashedPasswordFromDB(String nomeUsuario) {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        
        String query = "SELECT Senha FROM ContaUsuarios WHERE NomeUsuario = ?";
        
        try (Connection conn = conexaoBanco.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nomeUsuario);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Senha");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar a senha: " + e.getMessage());
        }
        return null;
    }
}
