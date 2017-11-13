package relatorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

public class RelatorioAssistidoFacade extends RelatorioFacade {

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
		
		
		// ==================================================================================================//
		//Cabecalho
		// ==================================================================================================//
		
		Cabecalho(document);
		
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
		
		PdfPCell nome      = new PdfPCell(new Paragraph("Nome",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 cpf       = new PdfPCell(new Paragraph("CPF",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 nascimento= new PdfPCell(new Paragraph("Nascimento",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 status    = new PdfPCell(new Paragraph("Status",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		
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
			
			cellCPF.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellNascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellsituacao.setHorizontalAlignment(Element.ALIGN_CENTER);
			
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
		
		PdfPCell totalAssistidos = new PdfPCell(new Paragraph("Total de Assitidos ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell numeroTotalAssistidos = new PdfPCell(new Paragraph(String.valueOf(Assistidoscontados)));
		
		totalAssistidos.setColspan(3);
		totalAssistidos.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalAssistidos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		numeroTotalAssistidos.setHorizontalAlignment(Element.ALIGN_CENTER);
		numeroTotalAssistidos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		table2.addCell(totalAssistidos);
		table2.addCell(numeroTotalAssistidos);
		table2.addCell("");
		table2.addCell("");

		document.add(table2);
		
		document.close();
	}
	

}
