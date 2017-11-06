package facade;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.Assistido;

public class RelatorioFacade {

	
	public void relatorioDeAssistido(ArrayList<Assistido> assistidos, String Subtitulo) throws DocumentException, IOException {
		//==================================================================================================//
		// step 1 - Criando o documento																		//
        //==================================================================================================//
		Document document = new Document();																	//
        // step 2 - setando o nome																			//
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("'DATA['dd'_'MM'_'yyyy'] HORA['HH.mm.ss']'");
        PdfWriter.getInstance(document, new FileOutputStream("Relatorios/Relatorio de Assistidos "+LocalDateTime.now().format(formatador) +".pdf"));
        // step 3 - Abrindo o documento																		//
        document.open();																					//
        //==================================================================================================//
        
        //adicionando um titulo no documento
        Paragraph titulo = new Paragraph("ONG Russas Transformando Vidas - ABRACE");
        Paragraph sub = new Paragraph(Subtitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        sub.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(sub);
        //==================================================================================================//
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(20);
        PdfPCell header = new PdfPCell(new Paragraph("Organização:"));
        table.getDefaultCell().setBackgroundColor(BaseColor.ORANGE);
        header.setBackgroundColor(BaseColor.ORANGE);
        header.setColspan(2);
        table.addCell(header);	
        table.addCell("Cidade:");
        table.addCell("estado:");
        table.addCell("Endereço:");
        table.addCell("CEP:");
        table.addCell("CNPJ:");
        table.addCell("Telefone:");
        table.addCell("E-mail:");
        DateTimeFormatter formatadorDataCadastro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        table.addCell("Emissão: "+LocalDateTime.now().format(formatadorDataCadastro));
        document.add(table);
        //==================================================================================================//
        PdfPTable table2 = new PdfPTable(4);
        table2.setSpacingBefore(20);
        table2.addCell("Nome");
        table2.addCell("Cpf");
        table2.addCell("Nascimento");
        table2.addCell("Status");
        //--------------------------------------------------------------------------------------------------//
        DateTimeFormatter formatadorDataNascimento = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Assistido assistido : assistidos) {
			table2.addCell(assistido.getNome());
			table2.addCell(assistido.getCpf());
			table2.addCell(assistido.getDataNasc().format(formatadorDataNascimento));
			table2.addCell(assistido.getSituacao()?"Vivo":"falecido");
		}
        table2.addCell("Total de assitidos");
        
        document.add(table2);
        

      //====================================================================================================//
      //step 1 - fechando o documento 																		//
      //====================================================================================================//
        document.close();																					//
      //====================================================================================================//
    }

}

