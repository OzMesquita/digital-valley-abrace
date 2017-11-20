package relatorio;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Doacao;
import model.PessoaFisica;
import model.PessoaJuridica;

public class RelatorioDoacaoFachada extends RelatorioFacade{
	public void relatorioDoacaoMensal(ArrayList<Doacao> list) throws DocumentException, IOException{

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

		DateTimeFormatter formatadorDataCadastro = DateTimeFormatter.ofPattern("MM");

		Paragraph sub = new Paragraph(subtitulo,new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		Paragraph emissao = new Paragraph("Referente ao mês " + LocalDateTime.now().format(formatadorDataCadastro));

		sub.setSpacingBefore(20);

		sub.setAlignment(Element.ALIGN_CENTER);
		emissao.setAlignment(Element.ALIGN_CENTER);

		document.add(sub);
		document.add(emissao);
		// ==================================================================================================//
		
		PdfPTable table2 = new PdfPTable(new float[] { 0.44f, 0.28f, 0.19f, 0.25f });
		table2.setSpacingBefore(20);

		PdfPCell doador      	= new PdfPCell(new Paragraph("Doador",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 cpfOrCNPJ   	= new PdfPCell(new Paragraph("CPF/CNPJ",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 dataDoacao     = new PdfPCell(new Paragraph("Data de doação",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				 valor          = new PdfPCell(new Paragraph("Valor doado",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));

		doador.setHorizontalAlignment(Element.ALIGN_CENTER);
		cpfOrCNPJ.setHorizontalAlignment(Element.ALIGN_CENTER);
		dataDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);
		valor.setHorizontalAlignment(Element.ALIGN_CENTER);

		doador.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cpfOrCNPJ.setBackgroundColor(BaseColor.LIGHT_GRAY);
		dataDoacao.setBackgroundColor(BaseColor.LIGHT_GRAY);
		valor.setBackgroundColor(BaseColor.LIGHT_GRAY);

		table2.addCell(doador);
		table2.addCell(cpfOrCNPJ);
		table2.addCell(dataDoacao);
		table2.addCell(valor);

		// --------------------------------------------------------------------------------------------------//
		
		DateTimeFormatter formatadorDataDoacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		float valorContado = 0;
		
		for (Doacao d : list) {
			PdfPCell cellDoador 			  = new PdfPCell(new Paragraph()), 
					 cellCPForCNPJ            = new PdfPCell(new Paragraph()), 
					 cellDataDoacao           = new PdfPCell(new Paragraph()),
					 cellValor			      = new PdfPCell(new Paragraph());

			if(d.getDoador() instanceof PessoaFisica){
				cellDoador 				= new PdfPCell(new Paragraph(d.getDoador().getNome()));
				cellCPForCNPJ          	= new PdfPCell(new Paragraph(((PessoaFisica)d.getDoador()).getCpf()));
				cellDataDoacao          = new PdfPCell(new Paragraph(d.getData().format(formatadorDataDoacao)));
				cellValor				= new PdfPCell(new Paragraph((NumberFormat.getCurrencyInstance().format(d.getValor()))));
				
				cellCPForCNPJ.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellDataDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);

				if (d.isAtivo()) {
					table2.addCell(cellDoador);
					table2.addCell(cellCPForCNPJ);
					table2.addCell(cellDataDoacao);
					table2.addCell(cellValor);
					valorContado += d.getValor(); 
				}
			}else
				if(d.getDoador() instanceof PessoaJuridica){
					cellDoador 				= new PdfPCell(new Paragraph(((PessoaJuridica) d.getDoador()).getNomeFantasia()));
					cellCPForCNPJ          	= new PdfPCell(new Paragraph(((PessoaJuridica) d.getDoador()).getCnpj()));
					cellDataDoacao          = new PdfPCell(new Paragraph(d.getData().format(formatadorDataDoacao)));
					cellValor				= new PdfPCell(new Paragraph((NumberFormat.getCurrencyInstance().format(d.getValor()))));
					
					cellCPForCNPJ.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellDataDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);

					if (d.isAtivo()) {
						table2.addCell(cellDoador);
						table2.addCell(cellCPForCNPJ);
						table2.addCell(cellDataDoacao);
						table2.addCell(cellValor);
						valorContado += d.getValor(); 
					}
				}
		}
		PdfPCell cellValorTotal = new PdfPCell(new Paragraph("Valor total ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell valorTotalDoado = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance().format(valorContado)));
		
		cellValorTotal.setColspan(3);
		cellValorTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellValorTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		valorTotalDoado.setBackgroundColor(BaseColor.LIGHT_GRAY);
		
		table2.addCell(cellValorTotal);
		table2.addCell(valorTotalDoado);
		table2.addCell("");
		table2.addCell("");

		document.add(table2);

		document.close();
	}
}
