package facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Table;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Assistido;

public class RelatorioFacade {

	public void relatorioDeAssistido(ArrayList<Assistido> assistidos, String Subtitulo, boolean exibirVivos, boolean exibirMortos) throws DocumentException, IOException {
		
		
		
		// ==================================================================================================//
		// step 1 - Criando o documento
		// ==================================================================================================//
		Document document = new Document();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("'DATA['dd'_'MM'_'yyyy'] HORA['HH.mm.ss']'");
		new File("Relatorios").mkdir();
		PdfWriter.getInstance(document, new FileOutputStream("Relatorios/Relatorio de Assistidos " + LocalDateTime.now().format(formatador) + ".pdf"));
		document.open();
		// ==================================================================================================//
		
		Cabecalho(document);

		// ==================================================================================================//
		//Cabecalho
		// ==================================================================================================//
		DateTimeFormatter formatadorDataCadastro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		Paragraph sub = new Paragraph(Subtitulo,new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		Paragraph emissao = new Paragraph("Emissão: " + LocalDateTime.now().format(formatadorDataCadastro));
		
		sub.setSpacingBefore(20);
		
		sub.setAlignment(Element.ALIGN_CENTER);
		emissao.setAlignment(Element.ALIGN_CENTER);
		
		document.add(sub);
		document.add(emissao);
		// ==================================================================================================//
		
		
		
		PdfPTable table2 = new PdfPTable(new float[] { 0.4f, 0.18f, 0.14f, 0.09f });
		table2.setSpacingBefore(20);
		
		PdfPCell nome      = new PdfPCell(new Paragraph("Nome")),
				 cpf       = new PdfPCell(new Paragraph("CPF")),
				 nascimento= new PdfPCell(new Paragraph("Nascimento")),
				 status    = new PdfPCell(new Paragraph("Status"));
		
		nome.setHorizontalAlignment(Element.ALIGN_CENTER);
		cpf.setHorizontalAlignment(Element.ALIGN_CENTER);
		nascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
		status.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		nome.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cpf.setBackgroundColor(BaseColor.LIGHT_GRAY);
		nascimento.setBackgroundColor(BaseColor.LIGHT_GRAY);
		status.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		table2.addCell(nome);
		table2.addCell(cpf);
		table2.addCell(nascimento);
		table2.addCell(status);
		
		// --------------------------------------------------------------------------------------------------//
		DateTimeFormatter formatadorDataNascimento = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		int Assistidoscontados = 0;
		
		for (Assistido assistido : assistidos) {
			PdfPCell cellNome       = new PdfPCell(new Paragraph(assistido.getNome())),
					 cellCPF        = new PdfPCell(new Paragraph(assistido.getCpf())),
					 cellNascimento = new PdfPCell(new Paragraph(assistido.getDataNasc().format(formatadorDataNascimento))),
					 cellsituacao   = new PdfPCell(new Paragraph(assistido.getSituacao() ? "vivo" : "falecido"));
			if (exibirVivos && assistido.getSituacao()) {
				table2.addCell(cellNome);
				table2.addCell(cellCPF);
				table2.addCell(cellNascimento);
				table2.addCell(cellsituacao);
				Assistidoscontados++;
			}
			if (exibirMortos && !assistido.getSituacao()) {
				table2.addCell(cellNome);
				table2.addCell(cellCPF);
				table2.addCell(cellNascimento);
				table2.addCell(cellsituacao);
				Assistidoscontados++;
			}
		}
		PdfPCell totalAssistidos = new PdfPCell(new Paragraph("Total de assitidos"));
		totalAssistidos.setColspan(3);
		table2.addCell(totalAssistidos);
		table2.addCell(String.valueOf(Assistidoscontados));
		table2.addCell("");
		table2.addCell("");

		document.add(table2);
		// ==================================================================================================//
		
		

		// ====================================================================================================//
		// step 1 - fechando o documento
		// ====================================================================================================//
		document.close();
		// ====================================================================================================//
	}

	private void Cabecalho(Document document) throws DocumentException {
		// ==================================================================================================//
		//Cabecalho
		// ==================================================================================================//
		Paragraph titulo = new Paragraph("ONG Russas Transformando Vidas - ABRACE",new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		titulo.setAlignment(Element.ALIGN_CENTER);
		document.add(titulo);
		// ==================================================================================================//  
		    
		PdfPTable table = new PdfPTable(2);
		    
		PdfPCell header = new PdfPCell(new Paragraph("Organização:"));
		PdfPCell email = new PdfPCell(new Paragraph("E-mail:"));
	        
		table.setSpacingBefore(20);
		    
		header.setColspan(2);
		email.setColspan(2);
		    
		table.addCell(header);
		table.addCell("CNPJ:");
		table.addCell("Endereço:");
		table.addCell("CEP:");
		table.addCell("Cidade:");
		table.addCell("estado:");
		table.addCell("Telefone:");
		table.addCell(email);
		table.addCell("");
		
		document.add(table);
	}

}
