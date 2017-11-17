package relatorio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import model.Doacao;

public class RelatorioDoacaoFachada extends RelatorioFacade{
	public void relatorioDoacaoMensal(List<Doacao> list) throws DocumentException, IOException{

		// ==================================================================================================//
		// Criando o documento
		// ==================================================================================================//
		Document document = new Document();
		String subtitulo = "Relatório mensal de doações da ONG ABRACE Russas";
		
		PdfWriter.getInstance(document, gravarDocumento(subtitulo));

		document.open();
		// ==================================================================================================//


		// ==================================================================================================//
		// Cabecalho
		// ==================================================================================================//

		Cabecalho(document);

		DateTimeFormatter formatadorDataCadastro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		Paragraph sub = new Paragraph(subtitulo,new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		Paragraph emissao = new Paragraph("Emissão: " + LocalDateTime.now().format(formatadorDataCadastro));

		sub.setSpacingBefore(20);

		sub.setAlignment(Element.ALIGN_CENTER);
		emissao.setAlignment(Element.ALIGN_CENTER);

		document.add(sub);
		document.add(emissao);
		// ==================================================================================================//



		document.close();
	}
}
