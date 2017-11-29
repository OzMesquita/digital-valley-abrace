package DAO;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import model.Usuario;

public class Instalacao {
	public static void instalarBanco() {
		   PreparedStatement stmt;
		   String[] createTable = {
				   "create schema ABRACE"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "CREATE TABLE ABRACE.Pessoa (" + 
				   " idPessoa INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," + 
				   " nome VARCHAR(128) NOT NULL," + 
				   " endereco VARCHAR(256) NOT NULL," + 
				   " telefone1 VARCHAR(16)," + 
				   " telefone2 VARCHAR(16)," + 
				   " email VARCHAR(128)," + 
				   " dataCadastro DATE NOT NULL," + 
				   " ativo BOOLEAN NOT NULL," + 
				   " isDoador BOOLEAN NOT NULL," +
				   " PRIMARY KEY (idPessoa)" + 
				   ")"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "CREATE TABLE ABRACE.Doacao (" + 
				   " IdDoacao INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," + 
				   " idPessoa INT NOT NULL," + 
				   " valor DOUBLE," + 
				   " data DATE," + 
				   " ativo BOOLEAN NOT NULL," +
				   " PRIMARY KEY (IdDoacao)," + 
				   " FOREIGN KEY (idPessoa) REFERENCES ABRACE.Pessoa (idPessoa) ON DELETE CASCADE ON UPDATE RESTRICT" + 
				   ")"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "CREATE TABLE ABRACE.Pessoa_Juridica (" + 
				   " idPessoa INT NOT NULL," + 
				   " cnpj VARCHAR(32) NOT NULL UNIQUE," +
				   " fantasia VARCHAR(128)," + 
				   " PRIMARY KEY (idPessoa)," + 
				   " FOREIGN KEY (idPessoa) REFERENCES ABRACE.Pessoa (idPessoa) ON DELETE CASCADE ON UPDATE RESTRICT" + 
				   ")"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "CREATE TABLE ABRACE.Pessoa_Fisica (" + 
				   " idPessoa INT NOT NULL," + 
				   " cpf VARCHAR(16) NOT NULL UNIQUE," + 
				   " rg VARCHAR(16) NOT NULL," + 
				   " dataNascimento DATE NOT NULL," + 
				   " PRIMARY KEY (idPessoa)," + 
				   " FOREIGN KEY (idPessoa) REFERENCES ABRACE.Pessoa (idPessoa) ON DELETE CASCADE ON UPDATE RESTRICT" + 
				   ")"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "CREATE TABLE ABRACE.Usuario (" + 
				   " idPessoa INT NOT NULL," + 
				   " login VARCHAR(32) NOT NULL," + 
				   " senha VARCHAR(32) NOT NULL," + 
				   " PRIMARY KEY (idPessoa)," + 
				   " FOREIGN KEY (idPessoa) REFERENCES ABRACE.Pessoa_Fisica (idPessoa) ON DELETE CASCADE ON UPDATE RESTRICT" + 
				   ")"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "CREATE TABLE ABRACE.Assistido (" + 
				   " idPessoa INT NOT NULL," + 
				   " tipoCancer VARCHAR(64) NOT NULL," + 
				   " apelido VARCHAR(32)," +
				   " dataEntrada DATE," +
				   " status BOOLEAN NOT NULL," + 
				   " PRIMARY KEY (idPessoa)," + 
				   " FOREIGN KEY (idPessoa) REFERENCES ABRACE.Pessoa_Fisica (idPessoa) ON DELETE CASCADE ON UPDATE RESTRICT" + 
				   ")"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "INSERT INTO ABRACE.PESSOA ( NOME,  ENDERECO,          TELEFONE1,             TELEFONE2,  EMAIL,                     DATACADASTRO,  ATIVO, ISDOADOR)" + 
				   "VALUES                    ('Usuário Teste', 'Vila Matoso, 82', '(88)99999-9999',      NULL,      'teste@n2s.com',  CURRENT_DATE, 'true', 'true'  )"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "INSERT INTO ABRACE.PESSOA_FISICA (IDPESSOA,                             CPF,              RG,      DATANASCIMENTO)" + 
				   "VALUES                           ((SELECT IDPESSOA FROM ABRACE.PESSOA), '089.612.933-03', '00000', '2017-01-01'  )"
				   //-----------------------------------//
				   ,
				   //-----------------------------------//
				   "INSERT INTO ABRACE.USUARIO (IDPESSOA,                             LOGIN,    SENHA)" + 
				   " VALUES                    ((SELECT IDPESSOA FROM ABRACE.PESSOA), 'abrace', 'abrace')"
				   };
		try {
			for (String sql : createTable) {
				stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
				stmt.execute();	
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void executarInstalacao() throws SQLException {
		DatabaseMetaData dbm = new ConnectionFactory().getConnection().getMetaData();
		// Verifica se o Schema"ABRACE" existe
		ResultSet tables = dbm.getSchemas(null, "ABRACE");
		// Se Schema"ABRACE" não existe então instala o banco
		if (!tables.next())
			instalarBanco();
	}
}