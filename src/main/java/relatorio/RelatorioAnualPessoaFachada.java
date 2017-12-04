package relatorio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
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
	public String relatorioAnualPessoa(Pessoa pessoa, int ano) throws DocumentException, MalformedURLException, IOException, DoacaoInvalidaException {
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
		PdfPCell endereco = new PdfPCell(new Paragraph("ENDEREÇO: "+pessoa.getEndereco()));
		PdfPCell email = new PdfPCell(new Paragraph("E-MAIL: "+pessoa.getEmail()));

		table.setSpacingBefore(20);
		table.getDefaultCell().setBorderColor(BaseColor.WHITE);

		
		nomeDoador.setColspan(2);
		nomeDoador.setBorderColor(BaseColor.WHITE);
		table.addCell(nomeDoador);
		
		if(!(pessoa instanceof PessoaFisica)) {
			nomeFantasia.setColspan(2);
			nomeFantasia.setBorderColor(BaseColor.WHITE);
			table.addCell(nomeFantasia);
		}
		
		table.addCell(((pessoa instanceof PessoaJuridica ) ? "CNPJ: " +(((PessoaJuridica) pessoa).getCnpj()): "CPF: "+(((PessoaFisica) pessoa).getCpf())));
		table.addCell(((pessoa instanceof PessoaFisica ) ? "RG: "+(((PessoaFisica) pessoa).getRg()):""));
		
		table.addCell(((pessoa instanceof PessoaFisica ) ? "DATA DE NASCIMENTO: "+(((PessoaFisica) pessoa).getDataNasc()).format(formatadorData):""));
		table.addCell("");
		
		endereco.setColspan(2);
		endereco.setBorderColor(BaseColor.WHITE);
		table.addCell(endereco);
		
		table.addCell("TELEFONE: "+pessoa.getTelefone());
		table.addCell("TELEFONE 2: "+pessoa.getTelefone2());
		
		email.setColspan(2);
		email.setBorderColor(BaseColor.WHITE);
		table.addCell(email);
		table.addCell("");

		document.add(table);

		//========================================================================================
		
		Paragraph sub = new Paragraph(subtitulo,new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		Paragraph emissao = new Paragraph("Referente ao ano de " + ano);

		sub.setSpacingBefore(20);

		sub.setAlignment(Element.ALIGN_CENTER);
		emissao.setAlignment(Element.ALIGN_CENTER);

		document.add(sub);
		document.add(emissao);

		//========================================================================================
		//				Tabela de doações
		//========================================================================================

		PdfPTable table2 = new PdfPTable(2);
		
		table2.setSpacingBefore(20);

		PdfPCell cellData = new PdfPCell(new Paragraph("Data da doação")),
				cellValor= new PdfPCell(new Paragraph("Valor doado"));

		cellData.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);

		cellData.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellValor.setBackgroundColor(BaseColor.LIGHT_GRAY);

		table2.addCell(cellData);
		table2.addCell(cellValor);

		//----------------------------------------------------------------------------------------

		double valorContado = 0;
		for(Doacao d : new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(pessoa)) {
			if(d.getData().getYear() == ano) {
				PdfPCell cellDataDoacao     = new PdfPCell(new Paragraph()),
						cellValorDoacao    = new PdfPCell(new Paragraph());
	
				cellDataDoacao      = new PdfPCell(new Paragraph(d.getData().format(formatadorData)));
				cellValorDoacao		= new PdfPCell(new Paragraph((NumberFormat.getCurrencyInstance().format(d.getValor()))));
	
				cellDataDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellValorDoacao.setHorizontalAlignment(Element.ALIGN_CENTER);
	
				if (d.isAtivo()) {
					table2.addCell(cellDataDoacao);
					table2.addCell(cellValorDoacao);
					valorContado += d.getValor(); 
				}
			}
		}
		PdfPCell cellValorTotal = new PdfPCell(new Paragraph("Valor total doado ",new Font(FontFamily.UNDEFINED,12,Font.BOLD)));
		PdfPCell valorTotalDoado = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance().format(valorContado)));

		cellValorTotal.setColspan(1);
		cellValorTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cellValorTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);

		valorTotalDoado.setBackgroundColor(BaseColor.LIGHT_GRAY);
		valorTotalDoado.setHorizontalAlignment(Element.ALIGN_CENTER);

		table2.addCell(cellValorTotal);
		table2.addCell(valorTotalDoado);

		document.add(table2);
		//========================================================================================
		document.close();
		return arquivoTMP;

	}
}
