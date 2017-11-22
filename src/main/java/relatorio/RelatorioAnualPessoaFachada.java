package relatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.time.LocalDate;
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

import DAO.ConnectionFactory;
import DAO.DoacaoDAO;
import DAO.PessoaFisicaDAO;
import exceptions.DoacaoInvalidaException;
import model.Doacao;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class RelatorioAnualPessoaFachada extends RelatorioFacade{
	public void relatorioAnualPessoa(Pessoa pessoa, int ano) throws DocumentException, MalformedURLException, IOException, DoacaoInvalidaException {
		Document document = new Document();
		String subtitulo = "Relatório anual de doações do doador "+ pessoa.getNome() +" para a ONG ABRACE Russas";
		PdfWriter.getInstance(document, gravarDocumento(subtitulo));

		document.open();

		Cabecalho(document);

		//========================================================================================
		//		Cabeçalho de dados do doador
		//========================================================================================
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		PdfPTable table = new PdfPTable(2);
		PdfPCell nomeDoador = new PdfPCell(new Paragraph("NOME DO DOADOR: "+pessoa.getNome()));
		PdfPCell nomeFantasia = new PdfPCell(new Paragraph("NOME FANTASIA: "+((pessoa instanceof PessoaJuridica ) ? (((PessoaJuridica) pessoa).getNomeFantasia()):"")));
		PdfPCell email = new PdfPCell(new Paragraph("E-MAIL: "+pessoa.getEmail()));

		table.setSpacingBefore(20);
		table.getDefaultCell().setBorderColor(BaseColor.WHITE);

		nomeDoador.setColspan(2);
		nomeDoador.setBorderColor(BaseColor.WHITE);

		nomeFantasia.setColspan(2);
		nomeFantasia.setBorderColor(BaseColor.WHITE);

		email.setColspan(2);
		email.setBorderColor(BaseColor.WHITE);

		table.addCell(nomeDoador);
		table.addCell(nomeFantasia);
		table.addCell("CPF/CNPJ: "+((pessoa instanceof PessoaJuridica ) ? (((PessoaJuridica) pessoa).getNomeFantasia()):(((PessoaFisica) pessoa).getCpf())));
		table.addCell("RG: "+((pessoa instanceof PessoaFisica ) ? (((PessoaFisica) pessoa).getRg()):""));
		table.addCell("ENDEREÇO: "+pessoa.getEndereco());
		table.addCell("DATA DE NASCIMENTO: "+((pessoa instanceof PessoaFisica ) ? (((PessoaFisica) pessoa).getDataNasc()).format(formatadorData):""));
		table.addCell("TELEFONE: "+pessoa.getTelefone());
		table.addCell("TELEFONE 2: "+pessoa.getTelefone2());
		table.addCell(email);
		table.addCell("");

		document.add(table);

		//========================================================================================


		//========================================================================================
		//				Tabela de doações
		//========================================================================================

		PdfPTable table2 = new PdfPTable(3);
		
		table2.setSpacingBefore(20);

		PdfPCell cellId  = new PdfPCell(new Paragraph("ID")), 
				cellData = new PdfPCell(new Paragraph("Data da doação")),
				cellValor= new PdfPCell(new Paragraph("Valor doado"));

		cellId.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellData.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);

		cellId.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellData.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellValor.setBackgroundColor(BaseColor.LIGHT_GRAY);

		table2.addCell(cellId);
		table2.addCell(cellData);
		table2.addCell(cellValor);

		//----------------------------------------------------------------------------------------

		double valorContado = 0;
		for(Doacao d : new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(1))) {
			if(d.getData().getYear() == ano) {
				PdfPCell cellIdDoacao 	   = new PdfPCell(new Paragraph()), 
						cellDataDoacao     = new PdfPCell(new Paragraph()),
						cellValorDoacao    = new PdfPCell(new Paragraph());
	
				cellIdDoacao 	 	= new PdfPCell(new Paragraph(String.valueOf(d.getId())));
				cellDataDoacao      = new PdfPCell(new Paragraph(d.getData().format(formatadorData)));
				cellValorDoacao		= new PdfPCell(new Paragraph((NumberFormat.getCurrencyInstance().format(d.getValor()))));
	
				cellIdDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellDataDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);
	
				if (d.isAtivo()) {
					table2.addCell(cellIdDoacao);
					table2.addCell(cellDataDoacao);
					table2.addCell(cellValorDoacao);
					valorContado += d.getValor(); 
				}
			}
		}
		PdfPCell cellValorTotal = new PdfPCell(new Paragraph("Valor total doado ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell valorTotalDoado = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance().format(valorContado)));

		cellValorTotal.setColspan(2);
		cellValorTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellValorTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);

		valorTotalDoado.setBackgroundColor(BaseColor.LIGHT_GRAY);

		table2.addCell(cellValorTotal);
		table2.addCell(valorTotalDoado);
		table2.addCell("");
		table2.addCell("");

		document.add(table2);
		//========================================================================================
		document.close();

	}
}
