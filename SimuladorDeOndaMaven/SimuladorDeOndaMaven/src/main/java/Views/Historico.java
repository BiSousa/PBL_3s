/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.Usuario;
import Models.ConexaoBanco;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author fernando.cruz
 */

public class Historico {
    public Historico(int UsuarioID){
        //Chama a classe de conexão com o banco de dados
        ConexaoBanco conexao = new ConexaoBanco();
        Usuario usuario = new Usuario();
        
        //Define parametros inicias da tela do 'Histórico'
        JFrame historico = new JFrame("Histórico");
        historico.setSize(920,320);
        historico.setResizable(false);
        historico.setLocationRelativeTo(null);
        historico.setVisible(true);
        
        //Cria o modelo central da tabela, juntamente com as colunas com os devidos nomes
        DefaultTableModel model = new DefaultTableModel(new String[]{"Frequência (Hz)", "Período (s)", "Comprimento da Onda (m)", "Tempo da Simulação (s)", "Erro Máximo (m)"}, 0){
        @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição de todas as células
            }
        };
        
        // Criando o renderizador de célula para centralizar o texto
        DefaultTableCellRenderer centralizarRenderer = new DefaultTableCellRenderer();
        centralizarRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento centralizado
        
        // Criando o objeto da tabela e colocando as colunas para organizar automaticamente
        JTable tabela = new JTable(model);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setRowHeight(50); // Define a altura das linhas para 50
        
        // Configurar a altura do cabeçalho das colunas
        JTableHeader header = tabela.getTableHeader();
        header.setPreferredSize(new java.awt.Dimension(80, 30)); 
        
        // Bloquear o movimento das colunast
        tabela.getTableHeader().setReorderingAllowed(false); 
        
        // Comando sql que está chamando uma VIEW dentro do banco de dados
        String sp_BistoricoSimulacoes = "{call sp_HistoricoSimulacoes(?)}";

        // Utilização do try-catch para realizar a conexão e fazer a consulta no banco de dados
        try(Connection conn = conexao.getConnection();
            CallableStatement cstmt = conn.prepareCall(sp_BistoricoSimulacoes);){
             
            int contarLinhas = 0;
            
            cstmt.setInt(1, UsuarioID);
            
            ResultSet rs = cstmt.executeQuery();
            
            //Enquanto o comando no banco de dados estiver sendo executado, os resultados são atribuidos nas colunas previamentes definidas
            while (rs.next()) {
                //int id = rs.getInt("ID");
                float frequencia = rs.getFloat("Frequencia");
                float periodo = rs.getFloat("Periodo");
                float comprimentoDaOnda = rs.getFloat("ComprimentoDaOnda");
                float tempoDaSimulacao = rs.getFloat("TempoDaSimulacao");
                float erroMaximo = rs.getFloat("ErroMaximo");
                model.addRow(new Object[]{frequencia, periodo, comprimentoDaOnda, tempoDaSimulacao, erroMaximo});
                contarLinhas ++;
            }
            
            if (contarLinhas == 0) {
                System.out.println("Nenhum registro encontrado na tabela.");
            } else {
                System.out.println("Registros encontrados: " + contarLinhas);
            }
            
            rs.close(); // Fecha a sessão de consulta sql no banco
            cstmt.close(); // Fecha statement
            conn.close(); // Fecha a conexão
        } catch (SQLException e) {
            e.printStackTrace(); // Caso seja gerada algum erro irá aparecer aqui
        }
        
        // Aplicando o renderizador a todas as colunas
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centralizarRenderer);
            tabela.setRowSelectionAllowed(false);
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setResizable(false);
        }
        
        // Adicionar a tabela em um JScrollPane para habilitar a rolagem
        JScrollPane pane = new JScrollPane(tabela);
        historico.getContentPane().add(pane);
    }
}
