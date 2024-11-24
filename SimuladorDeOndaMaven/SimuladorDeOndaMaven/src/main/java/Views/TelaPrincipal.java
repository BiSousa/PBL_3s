/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.EquacaoDaOnda;
import Controllers.SerieDeTaylor;
import Models.DadosInsert;
import Models.EncaminharEmail;
import Models.XeTInsert;
import Utils.RoundedBorder;
import Utils.SalvarGraficoComoImagem;
import Utils.VerificarCampoVazio;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.util.Range;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.Timer;
/**
 *
 * @author fernando.cruz
 */
public class TelaPrincipal {
               
    public TelaPrincipal(int UsuarioID){ 
        //Definindo parametros incias da tela principal
        JFrame janela = new JFrame("Simulador de Onda Elástica");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1280,720);
        janela.setLayout(null);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        
        // Criar menus
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuConfiguracoes = new JMenu("Configurações");
        JMenu menuAjuda = new JMenu("Sobre");

        // Criar itens de menu
        JMenuItem encaminharEmailItem = new JMenuItem("Encaminhar e-mail");
        JMenuItem sairItem = new JMenuItem("Sair");
        JMenuItem sobreItem = new JMenuItem("Sobre");
        JMenuItem eixoX = new JMenuItem("Eixo X");
        JMenuItem eixoY = new JMenuItem("Eixo Y");
        
        // Adicionar itens de menu aos menus
        menuArquivo.add(encaminharEmailItem);
        menuArquivo.addSeparator(); // Linha separadora
        menuArquivo.add(sairItem);
        menuConfiguracoes.add(eixoX);
        menuConfiguracoes.add(eixoY);

        //menuEditar.add(cortarItem); Colocar opção de desativar eixo x e y
        menuAjuda.add(sobreItem);

        // Adicionar menus à barra de menu
        menuBar.add(menuArquivo);
        menuBar.add(menuConfiguracoes);
        menuBar.add(menuAjuda);
        
        // Adicionar a barra de menu ao JFrame
        menuBar.setBackground(null);
        janela.setJMenuBar(menuBar);
        
        // Ação para o item "Encaminhar e-mail"
        encaminharEmailItem.addActionListener((ActionEvent e) -> {
            try {
                EncaminharEmail encaminharEmail = new EncaminharEmail();
                CriarDocumentoResultados documentoPdf = new CriarDocumentoResultados(UsuarioID);
                encaminharEmail.Email(UsuarioID);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Ação para o item "Sair"
        sairItem.addActionListener((ActionEvent e) -> {
            System.exit(0); // Fecha a aplicação
        });

        // Ação para o item "Sobre"
        sobreItem.addActionListener((ActionEvent e) -> {
            Sobre sobreView = new Sobre();
        });
        
        // Adicionando a área referente a "Frequência"
        JLabel frequenciaLabel = new JLabel("Frequência: ");
        JLabel frequenciaMedida = new JLabel("Hz");
        JPanel frequenciaPanel = new JPanel();
        JTextField frequencia = new JTextField(3);
        frequencia.setBounds(100, 90, 75, 30);
        frequenciaMedida.setBounds(180, 90, 75, 30);
        frequenciaPanel.setBounds(100, 60, 100, 15);
        frequenciaPanel.setLayout(new BorderLayout());
        frequencia.setToolTipText("Digite uma frequencia maior que 0 e menor que 0,5.");
        frequenciaPanel.add(frequenciaLabel);
        janela.getContentPane().add(frequenciaMedida);
        janela.getContentPane().add(frequenciaPanel);
        janela.getContentPane().add(frequencia);
        
        // Adicionando a área referente a "Comprimento de onda"
        JLabel comprimentoDaOndaLabel = new JLabel("Comprimento da onda: "); 
        JLabel comprimentoDaOndaMedida = new JLabel("Metros"); 
        JPanel comprimentoDaOndaPanel = new JPanel();
        JTextField comprimentoDaOnda = new JTextField(3);
        comprimentoDaOnda.setBounds(400, 90, 75, 30);
        comprimentoDaOndaMedida.setBounds(480,90,75,30);
        comprimentoDaOndaPanel.setBounds(400, 60, 200, 15);
        comprimentoDaOnda.setToolTipText("Digite o comprimento da onda maior que 0,2 e menor que 2.");
        comprimentoDaOndaPanel.setLayout(new BorderLayout());
        comprimentoDaOndaPanel.add(comprimentoDaOndaLabel);
        janela.getContentPane().add(comprimentoDaOndaMedida);
        janela.getContentPane().add(comprimentoDaOndaPanel);
        janela.getContentPane().add(comprimentoDaOnda);
        
        // Adicionando a área referente a "Duração da simulação"
        JLabel tempoDaSimulacaoLabel = new JLabel("Duração da simulação: ");
        JLabel tempoDaSimulacaoMedida = new JLabel("Segundos");
        JPanel tempoDaSimulacaoPanel = new JPanel();
        JTextField tempoDaSimulacao = new JTextField(3);
        tempoDaSimulacao.setBounds(700, 90, 75, 30);
        tempoDaSimulacaoMedida.setBounds(780,90,75,30);
        tempoDaSimulacaoPanel.setBounds(700, 60, 200, 15);
        tempoDaSimulacaoPanel.setLayout(new BorderLayout());
        tempoDaSimulacao.setToolTipText("Digite o tempo da simulação maior que 1 e menor que 10.");
        tempoDaSimulacaoPanel.add(tempoDaSimulacaoLabel);
        janela.getContentPane().add(tempoDaSimulacaoMedida);
        janela.getContentPane().add(tempoDaSimulacaoPanel);
        janela.getContentPane().add(tempoDaSimulacao);
        
        // Adicionando a área referente a "Erro máximo"
        JLabel erroMaximoLabel = new JLabel("Erro máximo: ");
        JLabel erroMaximoMedida = new JLabel("Metros");
        JPanel erroMaximoPanel = new JPanel();
        JTextField erroMaximo = new JTextField(6);
        erroMaximo.setBounds(1050,90,75,30);
        erroMaximoMedida.setBounds(1130,90,75,30);
        erroMaximoPanel.setBounds(1050, 60, 200, 15);
        erroMaximoPanel.setLayout(new BorderLayout());
        erroMaximo.setToolTipText("Digite o erro máximo tolerável maior que 0");
        erroMaximoPanel.add(erroMaximoLabel);
        janela.getContentPane().add(erroMaximoPanel);
        janela.getContentPane().add(erroMaximo);
        janela.getContentPane().add(erroMaximoMedida);
        
        // Adicionando a área referente o botão de "Calcular"
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setBounds(550, 160, 100, 30);
        calcularButton.setBorder(new RoundedBorder(30));
        calcularButton.setContentAreaFilled(false);
        calcularButton.setBackground(Color.white);
        janela.getContentPane().add(calcularButton);
        
        // Adicionando a área referente o botão de "Histórico"
        JButton historicoButton = new JButton("Histórico");
        historicoButton.setBounds(1150, 20, 100, 30);
        historicoButton.setBorder(new RoundedBorder(30));
        historicoButton.setContentAreaFilled(false);
        historicoButton.setBackground(Color.white);
        janela.getContentPane().add(historicoButton);
        
        //Quando está com o campo frequencia selecionado e é pressionado enter, pula para o próximo campo
        frequencia.addActionListener((ActionEvent e) -> {
            comprimentoDaOnda.requestFocus();
        });
        
        //Quando está com o campo comprimentoDaOnda selecionado e é pressionado enter, pula para o próximo campo
        comprimentoDaOnda.addActionListener((ActionEvent e) -> {
            tempoDaSimulacao.requestFocus();
        });
        
        //Quando está com o campo tempoDaSimulacao selecionado e é pressionado enter, pula para o próximo campo
        tempoDaSimulacao.addActionListener((ActionEvent e) -> {
            erroMaximo.requestFocus();
        });
        
        //Quando está com o campo erroMaximo selecionado e é pressionado enter, pula para o próximo campo
        erroMaximo.addActionListener((ActionEvent e) -> {
            frequencia.requestFocus();
        });
        
        //Quando é feito o clique encima do botão Historico, abre a classe historico
        historicoButton.addActionListener((ActionEvent e) -> {
            Historico historico = new Historico(UsuarioID);
        });
                    
        //Declaração das instacias da Serie de Taylor de seno e da Equcao da onda
        SerieDeTaylor seno = new SerieDeTaylor();
        EquacaoDaOnda equacao = new EquacaoDaOnda();
        
        //Criar um novo gráfico 2D
        Chart2D chart = new Chart2D();
        chart.setBackground(null);
        
        // Criar uma nova série de dados limitada (1000 pontos no máximo)
        ITrace2D trace = new Trace2DLtd(1000);
        trace.setName(" ");
        trace.setColor(Color.red);
        
        //Limitando a visualização do gráfico de y = 1 e x = 1
        chart.getAxisX().setRangePolicy(new RangePolicyFixedViewport(new Range(0, 1)));
        chart.getAxisY().setRangePolicy(new RangePolicyFixedViewport(new Range(-1, 1)));

        // dando ação ao botão "eixoX"
        eixoX.addActionListener(new ActionListener() {
            
            boolean isVisible = true; // Por padrão, o eixo X é visível
            
            public void actionPerformed(ActionEvent e) {
                // Alterna o estado da visibilidade
                isVisible = !isVisible;

                // Define a visibilidade do eixo X com base no estado atual
                chart.getAxisX().setVisible(isVisible);
                chart.repaint();
            }
        });
        
        // dando ação ao botão "eixoY"
        eixoY.addActionListener(new ActionListener() {

            boolean isVisible = true; // Por padrão, o eixo Y é visível
            
            public void actionPerformed(ActionEvent e) {
                
                // Alterna o estado da visibilidade
                isVisible = !isVisible;

                chart.getAxisY().setVisible(isVisible);
                chart.repaint();
            }
        });
       
        // Adicionar a série de dados ao gráfico
        chart.addTrace(trace);
        
        // Criando o painel para adicionar o Gráfico a ele
        JPanel panel = new JPanel();
        panel.setBounds(90, 250, 1080, 350);
        panel.setLayout(new BorderLayout());
        
        panel.add(chart);// Adiciona o gráfico ao painel
        
        janela.getContentPane().add(panel);// Adiciona o painel ao JFrame em uma área específica*/
        
        janela.setVisible(true);// Permite que a JFrame 'Janela' é visivel
        
        //Quando o usuário clica no botão 'Calcular' é chamado a classe de verificar campo vazio.
        //Caso tenha algum campo vazio, abre uma janela de 'erro' solicitando que seja incluido dados em todos os campos
        calcularButton.addActionListener((ActionEvent e) -> {
            VerificarCampoVazio verificarCampo = new VerificarCampoVazio();
            SalvarGraficoComoImagem salvar = new SalvarGraficoComoImagem();
            
            if(!verificarCampo.CampoVazio(frequencia, comprimentoDaOnda, tempoDaSimulacao, erroMaximo)){
                double frequenciaValor = Double.parseDouble(frequencia.getText());
                double comprimentoDaOndaValor = Double.parseDouble(comprimentoDaOnda.getText());
                double tempoDaSimulacaoValor = Double.parseDouble(tempoDaSimulacao.getText());
                double erroMaximoValor = Double.parseDouble(erroMaximo.getText());
                
                if(frequenciaValor <= 0 || frequenciaValor >= 0.5){
                    JOptionPane.showMessageDialog(janela, "O valor da frequència tem que ser maior que 0 e menor que 0.5");
                }else if(comprimentoDaOndaValor <= 0.2 || comprimentoDaOndaValor >= 2){
                    JOptionPane.showMessageDialog(janela, "O valor do comprimento da onda tem que ser maior que 0.2 e menor que 2");
                }else if(tempoDaSimulacaoValor <= 1 || tempoDaSimulacaoValor >= 10){
                    JOptionPane.showMessageDialog(janela, "O tempo da simulação tem que ser maior que 1 e menor que 10");
                }else if(erroMaximoValor <= 0){
                    JOptionPane.showMessageDialog(janela, "O erro máximo tem que ser maior que 0");
                }else{
                    DadosInsert dados = new DadosInsert();
                    dados.InserirDados(frequenciaValor, comprimentoDaOndaValor, tempoDaSimulacaoValor, erroMaximoValor, UsuarioID);
                    salvar.SalvarGraficoComoImagem(frequenciaValor, comprimentoDaOndaValor, tempoDaSimulacaoValor, erroMaximoValor, "D:\\Resultados\\SimulacaoCordaElasticaGrafico" + UsuarioID + ".png");
                    
                    // É necessário a criação de um vetor para que seja introzida na comparação IF (limitação é dada por conta da expressão lambda)
                    double[] tempo = {0.0};
                    
                    Timer timer = new Timer(50, (ActionEvent e1) -> {
                        if (tempo[0] < tempoDaSimulacaoValor) {
                            trace.removeAllPoints();
                            
                            double x = 0, y = 0;
                            
                            // Gerar e adicionar pontos da onda senoidal
                            int numPontos = 1000;
                            
                            for (int i = 0; i < numPontos; i++) {
                                x = i * 0.05; // valores de x ou t espaçados em 0.50
                                
                                y = seno.calcularSeno(equacao.Calcular(x, tempo[0], frequenciaValor, comprimentoDaOndaValor),erroMaximoValor);
                                
                                trace.addPoint(x, y);
                            }
                            
                            chart.repaint();
                    
                            double TempoArredondado = Math.round(tempo[0] * 100.0) / 100.0;

                            /*if(tempo[0] == 0){
                                System.out.println("Tempo = 0 \nValor de x: " + x + " Valor de tempo: " + TempoArredondado);
                            }else if(TempoArredondado == (tempoDaSimulacaoValor/2)){
                                System.out.println("Tempo = 2,5 \nValor de x: " + x + " Valor de tempo: " + TempoArredondado);
                            }else if(TempoArredondado == tempoDaSimulacaoValor){
                                System.out.println("Tempo = 5 \nValor de x: " + x + " Valor de tempo: " + TempoArredondado);
                            }*/        
                            
                            tempo[0] += 0.05; // vetor é definido como [0] só por conta da limitação após ser utilizada uma expressão lambda
                        } else {
                            ((Timer) e1.getSource()).stop();
                        }
                    });
                    
                    timer.start();
                }
            }else
                JOptionPane.showMessageDialog(janela, "Por favor, preencha todos os campos");   
        });
    }
}
