package relatorio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class RelatorioDoadoresFacade extends RelatorioFacade{

	public void relatorioDeDoadores(List<Pessoa> list, boolean exibirAtivos, boolean exibirInativos, boolean exibirPessoasFisicas, boolean exibirPessoasJuridicas) throws DocumentException, IOException{

		// ==================================================================================================//
		// Criando o documento
		// ==================================================================================================//
		Document document = new Document();
		String tipo = "";
		String tipoPessoa = "";
		
		if(exibirPessoasFisicas) tipoPessoa+=" Fisicas ";
		if(exibirPessoasFisicas&&exibirPessoasJuridicas) tipoPessoa+="e";
		if(exibirPessoasJuridicas) tipoPessoa+=" Juridicas ";
		
		if(exibirAtivos)tipo+=" Ativos ";
		if(exibirAtivos&&exibirInativos)tipo+="e";
		if(exibirInativos)tipo+=" Inativos ";
		
		String subtitulo = "Lista de Doadores"+tipo+"da ONG ABRACE Russas";
		String subtitulo2 = "Pessoas "+tipoPessoa+"";
		PdfWriter.getInstance(document, gravarDocumento("Lista de Doadores"+tipoPessoa+tipo+"da ONG ABRACE Russas"));

		document.open();
		// ==================================================================================================//


		// ==================================================================================================//
		// Cabecalho
		// ==================================================================================================//

		Cabecalho(document);

		DateTimeFormatter formatadorDataCadastro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		Paragraph sub = new Paragraph(subtitulo,new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		Paragraph sub2 = new Paragraph(subtitulo2,new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		Paragraph emissao = new Paragraph("Emissão: " + LocalDateTime.now().format(formatadorDataCadastro));

		sub.setSpacingBefore(20);

		sub.setAlignment(Element.ALIGN_CENTER);
		sub2.setAlignment(Element.ALIGN_CENTER);
		emissao.setAlignment(Element.ALIGN_CENTER);

		document.add(sub);
		document.add(sub2);
		document.add(emissao);
		// ==================================================================================================//

		PdfPTable table2 = new PdfPTable(new float[] { 0.4f, 0.18f, 0.09f });
		table2.setSpacingBefore(20);

		PdfPCell nomeOrNomeFantasia      = new PdfPCell(new Paragraph("Nome/Nome Fantasia",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				cpfOrCNPJ                = new PdfPCell(new Paragraph("CPF/CNPJ",new Font(FontFamily.UNDEFINED,12,Font.BOLD))),
				status                   = new PdfPCell(new Paragraph("Status",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));

		nomeOrNomeFantasia.setHorizontalAlignment(Element.ALIGN_CENTER);
		cpfOrCNPJ.setHorizontalAlignment(Element.ALIGN_CENTER);
		status.setHorizontalAlignment(Element.ALIGN_CENTER);

		nomeOrNomeFantasia.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cpfOrCNPJ.setBackgroundColor(BaseColor.LIGHT_GRAY);
		status.setBackgroundColor(BaseColor.LIGHT_GRAY);

		table2.addCell(nomeOrNomeFantasia);
		table2.addCell(cpfOrCNPJ);
		table2.addCell(status);

		// --------------------------------------------------------------------------------------------------//

		int doadoresContados = 0;

		for (Pessoa p : list) {
			PdfPCell cellNomeOrNomeFantasia = new PdfPCell(new Paragraph()), 
					 cellCPForCNPJ          = new PdfPCell(new Paragraph()), 
					 cellsituacao           = new PdfPCell(new Paragraph());

			if(p instanceof PessoaFisica&&exibirPessoasFisicas){
				cellNomeOrNomeFantasia = new PdfPCell(new Paragraph(p.getNome()));
				cellCPForCNPJ          = new PdfPCell(new Paragraph(((PessoaFisica) p).getCpf()));
				cellsituacao           = new PdfPCell(new Paragraph(p.isAtivo() ? "ativo" : "inativo"));
				
				cellCPForCNPJ.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellsituacao.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				if (exibirAtivos && p.isAtivo()) {
					table2.addCell(cellNomeOrNomeFantasia);
					table2.addCell(cellCPForCNPJ);
					table2.addCell(cellsituacao);
					doadoresContados++;
				}

				if (exibirInativos && !p.isAtivo()) {
					table2.addCell(cellNomeOrNomeFantasia);
					table2.addCell(cellCPForCNPJ);
					table2.addCell(cellsituacao);
					doadoresContados++;
				}
			}else
				if(p instanceof PessoaJuridica&&exibirPessoasJuridicas){
					cellNomeOrNomeFantasia = new PdfPCell(new Paragraph(((PessoaJuridica) p).getNomeFantasia()));
					cellCPForCNPJ          = new PdfPCell(new Paragraph(((PessoaJuridica) p).getCnpj()));
					cellsituacao           = new PdfPCell(new Paragraph(p.isAtivo() ? "ativo" : "inativo"));

					cellCPForCNPJ.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellsituacao.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					if (exibirAtivos && p.isAtivo()) {
						table2.addCell(cellNomeOrNomeFantasia);
						table2.addCell(cellCPForCNPJ);
						table2.addCell(cellsituacao);
						doadoresContados++;
					}

					if (exibirInativos && !p.isAtivo()) {
						table2.addCell(cellNomeOrNomeFantasia);
						table2.addCell(cellCPForCNPJ);
						table2.addCell(cellsituacao);
						doadoresContados++;
					}
				}
		}
		PdfPCell totalDoadores = new PdfPCell(new Paragraph("Total de Doadores ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell numeroTotalDoadores = new PdfPCell(new Paragraph(String.valueOf(doadoresContados)));

		totalDoadores.setColspan(2);
		totalDoadores.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalDoadores.setBackgroundColor(BaseColor.LIGHT_GRAY);

		numeroTotalDoadores.setHorizontalAlignment(Element.ALIGN_CENTER);
		numeroTotalDoadores.setBackgroundColor(BaseColor.LIGHT_GRAY);

		table2.addCell(totalDoadores);
		table2.addCell(numeroTotalDoadores);


		document.add(table2);

		document.close();
	}
}
