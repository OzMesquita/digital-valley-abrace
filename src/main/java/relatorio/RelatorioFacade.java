package relatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public abstract class RelatorioFacade {

	protected void Cabecalho(Document document) throws DocumentException, MalformedURLException, IOException {
		
		Image figura = Image.getInstance("src/main/java/view/img/ABRACE.png");
		figura.setAlignment(Element.ALIGN_CENTER);
		document.add(figura);
		
		Paragraph titulo = new Paragraph("ONG Russas Transformando Vidas - ABRACE",new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		titulo.setAlignment(Element.ALIGN_CENTER);
		document.add(titulo);
		    
		PdfPTable table = new PdfPTable(2);

		PdfPCell email = new PdfPCell(new Paragraph("E-MAIL: abrace.russas@gmail.com"));
	        
		table.setSpacingBefore(20);
		table.getDefaultCell().setBorderColor(BaseColor.WHITE);

		email.setColspan(2);
		email.setBorderColor(BaseColor.WHITE);

		table.addCell("CNPJ:");
		table.addCell("ENDEREÇO: Vila Matoso, 82");
		table.addCell("CEP: 62900-000");
		table.addCell("CIDADE: Russas");
		table.addCell("ESTADO: Ceará");
		table.addCell("TELEFONE: (85) 99935-7677");
		table.addCell(email);
		table.addCell("");
		
		document.add(table);
	}
	
	protected OutputStream gravarDocumento(String subtitulo) throws FileNotFoundException {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("'DATA['dd'_'MM'_'yyyy'] HORA['HH.mm.ss']'");
		new File("Relatorios").mkdir();
		return new FileOutputStream("Relatorios/"+subtitulo+" " + LocalDateTime.now().format(formatador) + ".pdf");
	}

}