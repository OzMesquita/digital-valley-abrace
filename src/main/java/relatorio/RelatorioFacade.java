package relatorio;

import java.io.IOException;
import java.net.MalformedURLException;

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
		    
		PdfPCell header = new PdfPCell(new Paragraph("ORGANIZAÇÃO: ONG Russas Transformando Vidas - ABRACE"));
		PdfPCell email = new PdfPCell(new Paragraph("E-MAIL: abrace.russas@gmail.com"));
	        
		table.setSpacingBefore(20);
		    
		header.setColspan(2);
		email.setColspan(2);
		    
		table.addCell(header);
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

}