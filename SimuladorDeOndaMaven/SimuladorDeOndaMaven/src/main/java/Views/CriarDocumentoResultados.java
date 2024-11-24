/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

/**
 *
 * @author fernando.cruz
 */

import Models.ValoresSimulacaoUsuarioID;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
    
public class CriarDocumentoResultados{
    public CriarDocumentoResultados(int UsuarioID) throws FileNotFoundException, MalformedURLException{
        
            ValoresSimulacaoUsuarioID valoresUsuarioID = new ValoresSimulacaoUsuarioID(UsuarioID);
        
            String destino = "D:\\Resultados\\SimulacaoCordaElastica" + UsuarioID + ".pdf";
            
            PdfWriter writer = new PdfWriter(new File(destino));
            PdfDocument pdfDoc = new PdfDocument(writer);
                
            Document documento = new Document(pdfDoc);
            
            // Estilo para o Título
            Style estiloDoTitulo = new Style()
                    .setFontSize(22)
                    .setBold()
                    .setFontColor(ColorConstants.BLUE)
                    .setTextAlignment(TextAlignment.CENTER);

            // Estilo para o Subtítulo
            Style estiloDoSubtitulo = new Style()
                    .setFontSize(18)
                    .setFontColor(ColorConstants.DARK_GRAY)
                    .setTextAlignment(TextAlignment.CENTER);

            // Título
            Text titulo = new Text("Simulação de Movimento de Uma Corda Elástica").addStyle(estiloDoTitulo);
            Paragraph tituloParagrafo = new Paragraph().add(titulo);
            documento.add(tituloParagrafo);

            // Subtítulo
            Text subtitle = new Text("Modelo de Análise de Dados e Resultados").addStyle(estiloDoSubtitulo);
            Paragraph subtituloParagrafo = new Paragraph().add(subtitle).setMarginBottom(15);
            documento.add(subtituloParagrafo);
            

            // Adicionando uma seção de descrição do cenário
            documento.add(new Paragraph("Cenário da Simulação")
                    .setFontSize(16)
                    .setBold()
                    .setFontColor(ColorConstants.BLACK));

            documento.add(new Paragraph("Este modelo representa uma simulação de movimento de uma corda elástica.")
                    .setFontSize(12)
                    .setMarginBottom(20));

            
            // Adicionando uma seção para os dados simulados
            documento.add(new Paragraph("Dados Simulados")
                    .setFontSize(16)
                    .setBold()
                    .setFontColor(ColorConstants.BLACK)
                    .setMarginTop(10));

            documento.add(new Paragraph("Os dados abaixo representam os valores gerados durante a simulação:")
                    .setFontSize(12)
                    .setMarginBottom(20));
            
            documento.add(new Paragraph("Frequência (Hz): " + String.format("%.2f", valoresUsuarioID.FrequenciaValor))
                    .setFontSize(12)
                    .setMarginBottom(5));
            
            documento.add(new Paragraph("Comprimento da Onda (m): " + String.format("%.2f", valoresUsuarioID.ComprimentoDaOndaValor))
                    .setFontSize(12)
                    .setMarginBottom(5));
            
            documento.add(new Paragraph("Tempo da simulação (s): " + String.format("%.2f", valoresUsuarioID.TempoDaSimulacaoValor))
                    .setFontSize(12)
                    .setMarginBottom(5));
            
            documento.add(new Paragraph("Erro máximo (m): " + String.format("%.2f", valoresUsuarioID.ErroMaximoValor))
                    .setFontSize(12)
                    .setMarginBottom(5));
            
            documento.add(new Paragraph("Imagem do gráfico: ")
                    .setFontSize(12)
                    .setMarginBottom(10)
                    .setTextAlignment(TextAlignment.CENTER));
            
            // Carregar e adicionar imagem
            String caminhoDaImagem = "D:\\Resultados\\SimulacaoCordaElasticaGrafico" + UsuarioID + ".png"; // Substitua pelo caminho da sua imagem
            ImageData imageData = ImageDataFactory.create(caminhoDaImagem);
            Image imagem = new Image(imageData);
            imagem.setWidth(1080); // Ajuste o tamanho conforme necessário
            imagem.setHeight(350);
            imagem.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
            documento.add(imagem);
            
            // Adicionando alguns números simulados para fins de design
            documento.close();
    }
}
