/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.EquacaoDaOnda;
import Utils.RoundedBorder;
import Controllers.SerieDeTaylor;
import Controllers.Usuario;
import Models.CadastroUsuario;
import Models.ConfirmarLogin;
import Models.TrocarSenha;
import Models.VerificarUsuario;
import Utils.VerificarCampoVazio;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.util.Range;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 *
 * @author fernando.cruz
 */
public class Login {
    
    public Login(){
        //Definindo parametros incias da tela principal
        JFrame janela = new JFrame("Tela de Login");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(720,480);
        janela.setLayout(null);
        janela.setBackground(Color.WHITE);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
        
        //Colocando o titulo 'login'
        JLabel login = new JLabel("LOGIN");
        login.setBounds(550,60,100,20);
        
        //Colocando a área de usuário
        JLabel usuarioLogin = new JLabel("Usuário: ");
        usuarioLogin.setBounds(450,105,100,20);
        JTextField usuarioLoginCampo = new JTextField();
        usuarioLoginCampo.setBorder(new RoundedBorder(30));
        usuarioLoginCampo.setBounds(510,100,150,40);
        usuarioLoginCampo.setBackground(null);
        
        //Colocando a área de senha
        JLabel senhaLogin = new JLabel("Senha: ");
        senhaLogin.setBounds(450,157,100,20);
        JPasswordField senhaLoginCampo = new JPasswordField(15);
        senhaLoginCampo.setColumns(6);
        senhaLoginCampo.setBorder(new RoundedBorder(30));
        senhaLoginCampo.setBounds(510,150,150,40);
        senhaLoginCampo.setBackground(null);
        
        //Colocando o botão de entrar
        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(530,210,100,30);
        loginButton.setBorder(new RoundedBorder(30));
        loginButton.setContentAreaFilled(false);
        
        //Colocando a área de criar conta, caso não exista
        JButton criarContaButton = new JButton("Criar conta");
        criarContaButton.setBounds(530,250,100,30);
        criarContaButton.setBorder(new RoundedBorder(30));
        criarContaButton.setContentAreaFilled(false);
        
        //Colocando a área de esqueci a senha
        JButton trocarASenhaButton = new JButton("Trocar a senha");
        trocarASenhaButton.setBounds(520,360,120,30);
        trocarASenhaButton.setBorder(new RoundedBorder(30));
        trocarASenhaButton.setContentAreaFilled(false);
        
        //Colocando a função do botão de entrar
        loginButton.addActionListener((ActionEvent e) -> {
            ConfirmarLogin confirmarLogin = new ConfirmarLogin();
            VerificarCampoVazio verificarCampo = new VerificarCampoVazio();
            VerificarUsuario usuario = new VerificarUsuario();
            
            String nomeUsuario = usuarioLoginCampo.getText();
            String senha = senhaLoginCampo.getText(); 
            
            if(!verificarCampo.CampoVazio(usuarioLoginCampo, senhaLoginCampo)){
                if(confirmarLogin.Login(nomeUsuario, senha) == true){
                   janela.setVisible(false);
                   int UsuarioID = usuario.VerificarUsuarioID(nomeUsuario);
                   
                   TelaPrincipal tela = new TelaPrincipal(UsuarioID);
                }else{
                    JOptionPane.showMessageDialog(janela, "Credenciais inválidas!");
                };
            } 
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
        
        // Define a visibilidade do eixo X e Y como false
        chart.getAxisX().setVisible(false);
        chart.getAxisY().setVisible(false);
        
        // Adicionar a série de dados ao gráfico
        chart.addTrace(trace);
        
        //Criando o painel para adicionar o Gráfico a ele
        JPanel panel = new JPanel();
        panel.setBounds(25,60,400,300);
        panel.setLayout(new BorderLayout());
        
        panel.add(chart);  // Adiciona o gráfico ao painel
        
        janela.getContentPane().add(panel);  // Adiciona o painel ao JFrame em uma área específica*/
        
        //É necessário a criação de um vetor para que seja introzida na comparação IF (limitação é dada por conta da expressão lambda)
        double[] tempo = {0.0};
                    
        Timer timer = new Timer(50, (ActionEvent e1) -> {
        if (tempo[0] < 300) {
            trace.removeAllPoints();
                            
            double x,y;
                            
            // Gerar e adicionar pontos da onda senoidal
            int numPontos = 1000;
                            
            for (int i = 0; i < numPontos; i++) {
                x = i * 0.05; // valores de x ou t espaçados em 0.50
                y = seno.calcularSeno(equacao.Calcular(x, tempo[0], 0.2, 1),1);
                                
                trace.addPoint(x, y);               
            }
                           
            chart.repaint();
                            
            tempo[0] += 0.05; // vetor é definido como [0] só por conta da limitação após ser utilizada uma expressão lambda
        } else {
                ((Timer) e1.getSource()).stop();
            }
        });
                    
        timer.start();
        
        //Colocando a função do botão de troca a senha
        trocarASenhaButton.addActionListener((ActionEvent e) -> {
            //limpa todos os componentes que estão na conta
            usuarioLogin.setVisible(false);
            usuarioLoginCampo.setVisible(false);
            senhaLogin.setVisible(false);
            senhaLoginCampo.setVisible(false);
            loginButton.setVisible(false);
            criarContaButton.setVisible(false);
            login.setVisible(false);
            chart.setVisible(false);
            panel.setVisible(false);
            trocarASenhaButton.setVisible(false);
            
            JLabel trocarSenha = new JLabel("TROCAR SENHA");
            trocarSenha.setBounds(320,40,100,50);
            
            //Colocando a área de usuário
            JLabel usuarioTrocarSenha = new JLabel("Usuário: ");
            usuarioTrocarSenha.setBounds(210,105,100,20);
            JTextField usuarioTrocarSenhaCampo = new JTextField();
            usuarioTrocarSenhaCampo.setBorder(new RoundedBorder(30));
            usuarioTrocarSenhaCampo.setBounds(270,100,200,40);
            usuarioTrocarSenhaCampo.setBackground(null);
            
            //Colocando a área de senha
            JLabel senhaTrocar = new JLabel("Senha atual: ");
            senhaTrocar.setBounds(185,157,100,20);
            JPasswordField senhaTrocarCampo = new JPasswordField(15);
            senhaTrocarCampo.setColumns(6);
            senhaTrocarCampo.setBorder(new RoundedBorder(30));
            senhaTrocarCampo.setBounds(270,150,200,40);
            senhaTrocarCampo.setBackground(null);
            
            //Colocando a área de confirmar senha
            JLabel senhaTrocarConfirmar = new JLabel("Confirmar Senha: ");
            senhaTrocarConfirmar.setBounds(155,207,150,20);
            JPasswordField senhaTrocarConfirmarCampo = new JPasswordField(15);
            senhaTrocarConfirmarCampo.setColumns(6);
            senhaTrocarConfirmarCampo.setBorder(new RoundedBorder(30));
            senhaTrocarConfirmarCampo.setBounds(270,200,200,40);
            senhaTrocarConfirmarCampo.setBackground(null);
            
            //Colocando a área de trocar a senha, caso não exista
            JButton trocarSenhaButton = new JButton("Trocar a senha");
            trocarSenhaButton.setBounds(290,270,150,30);
            trocarSenhaButton.setBorder(new RoundedBorder(30));
            trocarSenhaButton.setContentAreaFilled(false);
            
            //Colocando a área de voltar cadastro, caso não exista
            JButton voltarTrocarSenhaButton = new JButton("Voltar");
            voltarTrocarSenhaButton.setBounds(550,350,100,30);
            voltarTrocarSenhaButton.setBorder(new RoundedBorder(30));
            voltarTrocarSenhaButton.setContentAreaFilled(false);
            
            trocarSenhaButton.addActionListener((ActionEvent s) -> {
                TrocarSenha senha = new TrocarSenha();
                VerificarCampoVazio verificarCampo = new VerificarCampoVazio();
                
                if(!verificarCampo.CampoVazio(senhaTrocarCampo, senhaTrocarConfirmarCampo, usuarioTrocarSenhaCampo)){
                    String senhaAtual = senhaTrocarCampo.getText();
                    String senhaNova  = senhaTrocarConfirmarCampo.getText();
                    String usuarioInformado = usuarioTrocarSenhaCampo.getText();
                    
                    if(senhaAtual == senhaNova){
                        JOptionPane.showMessageDialog(janela, "Digite uma senha diferente da atual.");
                    }else{
                        senha.TrocarSenha(usuarioInformado, senhaAtual, senhaNova);
                        senhaTrocarCampo.setText("");
                        senhaTrocarConfirmarCampo.setText("");
                        usuarioTrocarSenhaCampo.setText("");
                        JOptionPane.showMessageDialog(janela, "Senha alterada com sucesso!");
                    }    
                }
                
            });
            
            voltarTrocarSenhaButton.addActionListener((ActionEvent r) -> {
                trocarSenhaButton.setVisible(false);
                voltarTrocarSenhaButton.setVisible(false);
                senhaTrocarConfirmar.setVisible(false);
                senhaTrocarConfirmarCampo.setVisible(false);
                senhaTrocar.setVisible(false);
                senhaTrocarCampo.setVisible(false);
                trocarSenha.setVisible(false);
                usuarioTrocarSenhaCampo.setVisible(false);
                usuarioTrocarSenha.setVisible(false);
                
                usuarioLogin.setVisible(true);
                usuarioLoginCampo.setVisible(true);
                senhaLogin.setVisible(true);
                senhaLoginCampo.setVisible(true);
                loginButton.setVisible(true);
                criarContaButton.setVisible(true);
                login.setVisible(true);
                chart.setVisible(true);
                panel.setVisible(true);
                trocarASenhaButton.setVisible(true);
            });
            
            janela.add(usuarioTrocarSenha);
            janela.add(usuarioTrocarSenhaCampo);
            janela.add(trocarSenhaButton);
            janela.add(voltarTrocarSenhaButton);
            janela.add(senhaTrocarConfirmar);
            janela.add(senhaTrocarConfirmarCampo);
            janela.add(senhaTrocar);
            janela.add(senhaTrocarCampo);
            janela.add(trocarSenha);
        });
                
        //Colocando a função do botão de criar conta
        criarContaButton.addActionListener((ActionEvent e) -> {
            //limpa todos os componentes que estão na conta
            usuarioLogin.setVisible(false);
            usuarioLoginCampo.setVisible(false);
            senhaLogin.setVisible(false);
            senhaLoginCampo.setVisible(false);
            loginButton.setVisible(false);
            criarContaButton.setVisible(false);
            login.setVisible(false);
            chart.setVisible(false);
            panel.setVisible(false);
            trocarASenhaButton.setVisible(false);

            JLabel criarConta = new JLabel("CRIAR CONTA");
            criarConta.setBounds(320,40,100,50);
            
            //Colocando a área de usuário
            JLabel usuarioCadastro = new JLabel("Usuário: ");
            usuarioCadastro.setBounds(210,105,100,20);
            JTextField usuarioCadastroCampo = new JTextField();
            usuarioCadastroCampo.setBorder(new RoundedBorder(30));
            usuarioCadastroCampo.setBounds(270,100,200,40);
            usuarioCadastroCampo.setBackground(null);

            //Colocando a área de senha
            JLabel senhaCadastro = new JLabel("Senha: ");
            senhaCadastro.setBounds(210,207,100,20);
            JPasswordField senhaCadastroCampo = new JPasswordField(15);
            senhaCadastroCampo.setColumns(6);
            senhaCadastroCampo.setBorder(new RoundedBorder(30));
            senhaCadastroCampo.setBounds(270,200,200,40);
            senhaCadastroCampo.setBackground(null);
            
            //Colocando a área de email
            JLabel emailCadastro = new JLabel("E-mail: ");
            emailCadastro.setBounds(210,157,100,20);
            JTextField emailCadastroCampo = new JTextField(50);
            emailCadastroCampo.setColumns(6);
            emailCadastroCampo.setBorder(new RoundedBorder(30));
            emailCadastroCampo.setBounds(270,150,200,40);
            emailCadastroCampo.setBackground(null);
            
            //Colocando a área de criar conta, caso não exista
            JButton criarContaCadastroButton = new JButton("Criar conta");
            criarContaCadastroButton.setBounds(310,270,100,30);
            criarContaCadastroButton.setBorder(new RoundedBorder(30));
            criarContaCadastroButton.setContentAreaFilled(false);
            
            //Colocando a área de criar conta, caso não exista
            JButton voltarCadastroButton = new JButton("Voltar");
            voltarCadastroButton.setBounds(550,350,100,30);
            voltarCadastroButton.setBorder(new RoundedBorder(30));
            voltarCadastroButton.setContentAreaFilled(false);
            
            criarContaCadastroButton.addActionListener((ActionEvent a) -> {
                VerificarCampoVazio verificarCampo = new VerificarCampoVazio();
                Usuario usuario = new Usuario();
                
                if(!verificarCampo.CampoVazio(usuarioCadastroCampo, senhaCadastroCampo, emailCadastroCampo)){
                    usuario.setNome(usuarioCadastroCampo.getText());
                    usuario.setSenha(senhaCadastroCampo.getText());
                    usuario.setEmail(emailCadastroCampo.getText());
                    
                    String usuarioNome = usuario.getNome();
                    String emailNome = usuario.getEmail();
                    String senhaNome = usuario.getSenha();
                    
                    LocalDate dataAtual = LocalDate.now();
                    
                    CadastroUsuario cadastro = new CadastroUsuario();
                    cadastro.InserirDados(usuarioNome, senhaNome, emailNome, dataAtual);
                    
                    usuarioCadastroCampo.setText("");
                    senhaCadastroCampo.setText("");
                    emailCadastroCampo.setText("");
                    JOptionPane.showMessageDialog(janela, cadastro.mensagem);
                }
            });
            
            voltarCadastroButton.addActionListener((ActionEvent t) -> {
                criarContaCadastroButton.setVisible(false);
                emailCadastro.setVisible(false);
                emailCadastroCampo.setVisible(false);
                usuarioCadastro.setVisible(false);
                usuarioCadastroCampo.setVisible(false);
                senhaCadastro.setVisible(false);
                senhaCadastroCampo.setVisible(false);
                criarConta.setVisible(false);
                voltarCadastroButton.setVisible(false);

                usuarioLogin.setVisible(true);
                usuarioLoginCampo.setVisible(true);
                senhaLogin.setVisible(true);
                senhaLoginCampo.setVisible(true);
                loginButton.setVisible(true);
                criarContaButton.setVisible(true);
                login.setVisible(true);
                chart.setVisible(true);
                panel.setVisible(true);
                trocarASenhaButton.setVisible(true);
            });
            
            janela.add(voltarCadastroButton);
            janela.add(criarContaCadastroButton);
            janela.add(emailCadastro);
            janela.add(emailCadastroCampo);
            janela.add(usuarioCadastro);
            janela.add(usuarioCadastroCampo);
            janela.add(senhaCadastro);
            janela.add(senhaCadastroCampo);
            janela.add(criarConta);
        });
        
        janela.add(trocarASenhaButton);
        janela.add(login);
        janela.add(loginButton);
        janela.add(senhaLoginCampo);
        janela.add(usuarioLoginCampo);
        janela.add(senhaLogin);
        janela.add(usuarioLogin);
        janela.add(criarContaButton);
        
        janela.setVisible(true);
    }
}
    
