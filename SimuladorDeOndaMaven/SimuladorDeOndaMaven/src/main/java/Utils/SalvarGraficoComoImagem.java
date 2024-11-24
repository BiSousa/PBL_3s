package Utils;

import Controllers.EquacaoDaOnda;
import Controllers.SerieDeTaylor;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.util.Range;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;


public class SalvarGraficoComoImagem{
    public void SalvarGraficoComoImagem(double frequenciaValor, double comprimentoValor, double tempoValor, double erroValor, String filename) {
        // Configurar o buffer de imagem e o contexto gráfico
        BufferedImage image = new BufferedImage(1080, 350, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        //Declaração das instacias da Serie de Taylor de seno e da Equcao da onda
        SerieDeTaylor seno = new SerieDeTaylor();
        EquacaoDaOnda equacao = new EquacaoDaOnda();
        
        //Criar um novo gráfico 2D
        Chart2D imagemDoGrafico = new Chart2D();
        imagemDoGrafico.setSize(1080, 350);
        imagemDoGrafico.setBackground(Color.WHITE);
        
        // Criar uma nova série de dados limitada (1000 pontos no máximo)
        ITrace2D trace = new Trace2DLtd(1000);
        trace.setName(" ");
        trace.setColor(Color.red);
        
        //Limitando a visualização do gráfico de y = 1 e x = 1
        imagemDoGrafico.getAxisX().setRangePolicy(new RangePolicyFixedViewport(new Range(0, 10)));
        imagemDoGrafico.getAxisY().setRangePolicy(new RangePolicyFixedViewport(new Range(-1, 1)));
        
        imagemDoGrafico.getAxisY().setVisible(true);
        imagemDoGrafico.getAxisX().setVisible(true);
        
        // Adicionar a série de dados ao gráfico
        imagemDoGrafico.addTrace(trace);
        
        // Gerar e adicionar pontos da onda senoidal
        int numPontos = 1000;
        
        for (int i = 0; i < numPontos; i++) {
            double x = i * 0.05; // valores de x ou t espaçados em 0.50
                                
            double y = seno.calcularSeno(equacao.Calcular(x, 0, frequenciaValor, comprimentoValor),erroValor);
                                
            trace.addPoint(x, y);
        } 
        
        // Renderizar o gráfico no buffer de imagem
        imagemDoGrafico.paint(g2);

        // Salvar a imagem em arquivo
        try {
            ImageIO.write(image, "PNG", new File(filename));
        } catch (IOException e) {
            System.err.println("Erro ao salvar a imagem: " + e.getMessage());
        }
    }
}