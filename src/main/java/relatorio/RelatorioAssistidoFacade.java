package relatorio;

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
	
	public String relatorioDeAssistido(ArrayList<Assistido> assistidos, boolean exibirVivos, boolean exibirMortos) throws DocumentException, IOException {
		
		// ==================================================================================================//
		// Criando o documento
		// ==================================================================================================//
		Document document = new Document();
		String tipo = "";
		if(exibirVivos)tipo+="Vivos";
		if(exibirVivos&&exibirMortos)tipo+=" e ";
		if(exibirMortos)tipo+="Falecidos";
		String subtitulo = "Relatório de Assistidos "+tipo+" da ONG ABRACE Russas";
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
		
		
		
		PdfPTable table2 = new PdfPTable(new float[] { 0.38f, 0.2f, 0.16f, 0.11f });
		table2.setSpacingBefore(20);
		
		PdfPCell nome      = new PdfPCell(new Paragraph("Nome",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 cpf       = new PdfPCell(new Paragraph("CPF",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 nascimento= new PdfPCell(new Paragraph("Data de nascimento",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 status    = new PdfPCell(new Paragraph("Status",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		
		nome.setHorizontalAlignment(Element.ALIGN_CENTER);
		nome.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cpf.setHorizontalAlignment(Element.ALIGN_CENTER);
		cpf.setVerticalAlignment(Element.ALIGN_MIDDLE);
		nascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
		nascimento.setVerticalAlignment(Element.ALIGN_MIDDLE);
		status.setHorizontalAlignment(Element.ALIGN_CENTER);
		status.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
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
		int assistidoVivo = 0;
		int assistidoMorto = 0;
		
		for (Assistido assistido : assistidos) {
			
			PdfPCell cellNome       = new PdfPCell(new Paragraph(assistido.getNome())),
					 cellCPF        = new PdfPCell(new Paragraph(assistido.getCpf())),
					 cellNascimento = new PdfPCell(new Paragraph(assistido.getDataNasc().format(formatadorDataNascimento))),
					 cellsituacao   = new PdfPCell(new Paragraph(assistido.getSituacao() ? "Vivo" : "Falecido"));
			
			cellCPF.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellNascimento.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellsituacao.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			if (exibirVivos && assistido.getSituacao()) {
				table2.addCell(cellNome);
				table2.addCell(cellCPF);
				table2.addCell(cellNascimento);
				table2.addCell(cellsituacao);
				Assistidoscontados++;
				assistidoVivo++;
			}
			
			if (exibirMortos && !assistido.getSituacao()) {
				table2.addCell(cellNome);
				table2.addCell(cellCPF);
				table2.addCell(cellNascimento);
				table2.addCell(cellsituacao);
				Assistidoscontados++;
				assistidoMorto++;
			}
			
		}
		
		PdfPCell totalAssistidosVivos = new PdfPCell(new Paragraph("Assistidos Vivos ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell totalAssistidosMortos = new PdfPCell(new Paragraph("Assitidos Falecidos ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell totalAssistidos = new PdfPCell(new Paragraph("Total de Assitidos ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		
		PdfPCell numeroTotalAssistidosVivos = new PdfPCell(new Paragraph(String.valueOf(assistidoVivo)));
		PdfPCell numeroTotalAssistidosMortos = new PdfPCell(new Paragraph(String.valueOf(assistidoMorto)));
		PdfPCell numeroTotalAssistidos = new PdfPCell(new Paragraph(String.valueOf(Assistidoscontados)));
		
		totalAssistidos.setColspan(3);
		totalAssistidos.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalAssistidos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		totalAssistidosVivos.setColspan(3);
		totalAssistidosVivos.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalAssistidosVivos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		totalAssistidosMortos.setColspan(3);
		totalAssistidosMortos.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalAssistidosMortos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		numeroTotalAssistidosVivos.setHorizontalAlignment(Element.ALIGN_CENTER);
		numeroTotalAssistidosVivos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		numeroTotalAssistidosMortos.setHorizontalAlignment(Element.ALIGN_CENTER);
		numeroTotalAssistidosMortos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		numeroTotalAssistidos.setHorizontalAlignment(Element.ALIGN_CENTER);
		numeroTotalAssistidos.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		if(exibirVivos) {
			table2.addCell(totalAssistidosVivos);
			table2.addCell(numeroTotalAssistidosVivos);
		}
		
		if(exibirMortos) {
			table2.addCell(totalAssistidosMortos);
			table2.addCell(numeroTotalAssistidosMortos);
		}
		
		if(exibirMortos && exibirVivos) {
			table2.addCell(totalAssistidos);
			table2.addCell(numeroTotalAssistidos);
			table2.addCell("");
			table2.addCell("");
		}

		document.add(table2);
		
		document.close();
		return arquivoTMP;
	}

	
	

}
