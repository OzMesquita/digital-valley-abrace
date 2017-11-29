package relatorio;

import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.Doacao;
import model.PessoaFisica;
import model.PessoaJuridica;
import relatorio.Extenso;

public class ReciboDoacaoFachada extends RelatorioFacade{
	public ReciboDoacaoFachada() {
		
	}
	
	public String reciboDoadorFisico(Doacao doacao) throws DocumentException, IOException {
		// ==================================================================================================//
		// Criando o documento
		// ==================================================================================================//
		Document document = new Document();
		String subtitulo = "Recibo de Doa��o";
		PdfWriter.getInstance(document, gravarDocumento(subtitulo));
		document.open();
		// ==================================================================================================//
		
		Image figura = Image.getInstance("src/main/java/view/img/ABRACE.png");
		figura.setAlignment(Element.ALIGN_CENTER);
		document.add(figura);
		
		Paragraph titulo = new Paragraph(subtitulo, new Font(FontFamily.UNDEFINED,12,Font.BOLD));
		titulo.setAlignment(Element.ALIGN_CENTER);
		titulo.setSpacingAfter(20);
		titulo.setSpacingBefore(20);
		document.add(titulo);
		
		String conteudo = "Atrav�s deste documento, declaro, para todos os fins e a quem possa interessar que recebi de " + doacao.getDoador().getNome() + 
				", Carteira de Identidade (RG) n�mero: " + (((PessoaFisica) doacao.getDoador()).getRg()) +
				", CPF n�mero: " + (((PessoaFisica) doacao.getDoador()).getCpf()) +
				" a quantia de R$ " + doacao.getValor() + " ("+new Extenso(doacao.getValor())+"), " +
				"referente a doa��o para a ONG (Organiza��o Sem Fins Lucrativos) Russas Transformando Vidas - ABRACE. Por ser verdade, assino a presente.";
		
		Paragraph declaracao = new Paragraph(conteudo,new Font(FontFamily.UNDEFINED, 11));
		declaracao.setAlignment(Element.ALIGN_JUSTIFIED);
		
		document.add(declaracao);
		
		String localData = "..............................., ....... de ........................... de ............";
		Paragraph local = new Paragraph(localData, new Font(FontFamily.UNDEFINED, 10));
		local.setAlignment(Element.ALIGN_CENTER);
		local.setSpacingBefore(15);
		document.add(local);
		
		String inf = "(Local e data da assinatura)";
		Paragraph informacao = new Paragraph(inf, new Font(FontFamily.UNDEFINED, 10));
		informacao.setAlignment(Element.ALIGN_CENTER);
		document.add(informacao);
		
		String ass = "___________________________________________________________";
		Paragraph assinatura = new Paragraph(ass, new Font(FontFamily.UNDEFINED, 10));
		assinatura.setAlignment(Element.ALIGN_CENTER);
		assinatura.setSpacingBefore(25);
		document.add(assinatura);
		
		document.close();
		return arquivoTMP;
	}
	
	public String reciboDoadorJuridico(Doacao doacao) throws DocumentException, MalformedURLException, IOException {
		// ==================================================================================================//
				// Criando o documento
				// ==================================================================================================//
				Document document = new Document();
				String subtitulo = "Recibo de Doa��o";
				PdfWriter.getInstance(document, gravarDocumento(subtitulo));
				document.open();
				// ==================================================================================================//
				
				Image figura = Image.getInstance("src/main/java/view/img/ABRACE.png");
				figura.setAlignment(Element.ALIGN_CENTER);
				document.add(figura);
				
				Paragraph titulo = new Paragraph(subtitulo, new Font(FontFamily.UNDEFINED,12,Font.BOLD));
				titulo.setAlignment(Element.ALIGN_CENTER);
				titulo.setSpacingAfter(20);
				titulo.setSpacingBefore(20);
				document.add(titulo);
				
				String conteudo = "Atrav�s deste documento, declaro, para todos os fins e a quem possa interessar que recebi de " + doacao.getDoador().getNome() + 
						", CNPJ n�mero: " + (((PessoaJuridica) doacao.getDoador()).getCnpj()) +
						", com sede em " + doacao.getDoador().getEndereco() +
						" a quantia de R$ " + doacao.getValor() + " ("+new Extenso(doacao.getValor())+"), " +
						"referente a doa��o para a ONG (Organiza��o Sem Fins Lucrativos) Russas Transformando Vidas - ABRACE. Por ser verdade, assino a presente.";
				
				Paragraph declaracao = new Paragraph(conteudo,new Font(FontFamily.UNDEFINED, 11));
				declaracao.setAlignment(Element.ALIGN_JUSTIFIED);
				
				document.add(declaracao);
				
				String localData = "..............................., ....... de ........................... de ............";
				Paragraph local = new Paragraph(localData, new Font(FontFamily.UNDEFINED, 10));
				local.setAlignment(Element.ALIGN_CENTER);
				local.setSpacingBefore(15);
				document.add(local);
				
				String inf = "(Local e data da assinatura)";
				Paragraph informacao = new Paragraph(inf, new Font(FontFamily.UNDEFINED, 10));
				informacao.setAlignment(Element.ALIGN_CENTER);
				document.add(informacao);
				
				String ass = "___________________________________________________________";
				Paragraph assinatura = new Paragraph(ass, new Font(FontFamily.UNDEFINED, 10));
				assinatura.setAlignment(Element.ALIGN_CENTER);
				assinatura.setSpacingBefore(25);
				document.add(assinatura);
				
				document.close();
				return arquivoTMP;
	}
}
