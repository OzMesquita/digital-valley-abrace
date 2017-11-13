package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.DocumentException;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.Assistido;
import model.Pessoa;
import relatorio.RelatorioAssistidoFacade;

public class RelatorioDAOTest {


	public static void main(String[] args) {
		try {
			new RelatorioAssistidoFacade().relatorioDeAssistido(new AssistidoDAO(new ConnectionFactory().getConnection()).listaAssistido(),true,true);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
