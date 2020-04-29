package com.qintess.application;
import java.util.Scanner;

import com.qintess.model.LivroDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {

	public static void main(String[] args) {
		System.out.println(new LivroDao().buscaPorTodos(2));
		
		
		/*try {
			//caminho para achar o drive
			Class.forName("org.h2.Driver");
			//criar conexao
			Connection test = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			//declarando coisas na conexao utilizada
			Statement comandos = test.createStatement();
			//string de apoio para o codigo SQL
			*/
			/*String comandoSql = "CREATE TABLE Livro("
					+ "idlivro INTEGER PRIMARY KEY,"
					+ "titulo VARCHAR (100) NOT NULL,"
					+ "preco FLOAT,"
					+ "estoque INTEGER,"
					+ "idgenero INTEGER)";*/
			
			//executando o codigo SQL
		//	comandos.execute(comandoSql);
			System.out.println("Executado com sucess!");
			
		} catch (SQLException e) {   //excessao SQL
			// TODO Auto-generated catch block
			e.printStackTrace();  //printar na tela o erro
			
		} catch (ClassNotFoundException e) { //nao achar classe driver ele retorna o erro
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/*
 * import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.qintess.jdbc.modelo.Cliente;
import com.qintess.jdbc.modelo.Autor;
import com.qintess.jdbc.modelo.ConnectionFactory;
import com.qintess.jdbc.modelo.CreateBank;
import com.qintess.jdbc.modelo.Escreve;
import com.qintess.jdbc.modelo.ItemVenda;
import com.qintess.jdbc.modelo.Livro;
import com.qintess.jdbc.modelo.Venda;

public class Execute {
	static Scanner scanner = new Scanner(System.in);
	static Connection conn = ConnectionFactory.getConnection();
	public static void main(String[] args) throws SQLException {
		controlPanel();
		scanner.close();
	}
	private static void controlPanel() throws SQLException {
		CreateBank createBank = new CreateBank();
		createBank.createBank();
		System.out.println("=============LIVRARIA=============");
		System.out.print(
				"1- Inserir\n2- Ler\n3- Alterar\n4- Deletar\n5- Relatório\n6- Sair\n" + "Escolha (1/2/3/4/5/6): ");
		String decision = scanner.nextLine();
		String choice = "S";
		switch (decision) {
		case "1": {
			while (!choice.equalsIgnoreCase("n")) {
				findItemVenda("insert");
				System.out.print("Deseja inserir outra informação? (S/n): ");
				choice = scanner.nextLine();
			}

			controlPanel();
			break;
		}
		case "2": {
			while (!choice.equalsIgnoreCase("n")) {
				findItemVenda("read");
				System.out.print("Deseja ler outra tabela? (S/n): ");
				choice = scanner.nextLine();
			}

			controlPanel();
			break;
		}
		case "3": {
			while (!choice.equalsIgnoreCase("n")) {
				findItemVenda("alter");
				System.out.print("Deseja alterar outra tabela? (S/n): ");
				choice = scanner.nextLine();
			}

			controlPanel();
			break;
		}
		case "4": {
			while (!choice.equalsIgnoreCase("n")) {
				findItemVenda("delete");
				System.out.print("Deseja deletar outra informação? (S/n): ");
				choice = scanner.nextLine();
			}

			controlPanel();
			break;
		}
		case "5": {
			while (!choice.equalsIgnoreCase("n")) {
				report();
				System.out.print("Deseja imprimir novamente o relatório? (S/n): ");
				choice = scanner.nextLine();
			}

			controlPanel();
			break;
		}
		case "6": {
			System.out.println("Finalizando programa...");
			break;
		}
		default:
			throw new IllegalArgumentException("Valor inesperado: " + decision);
		}
	}

	private static void report() throws SQLException {
		String sqlLivrosVendidos = "SELECT l.idlivro, l.titulo, sum(iv.qtd) FROM livros l\r\n" + 
				"INNER JOIN  itens_de_vendas iv\r\n" + 
				"ON iv.idlivro = l.idlivro\r\n" + 
				"GROUP BY l.titulo;";
		Statement sLV = conn.createStatement();
		ResultSet rLV = sLV.executeQuery(sqlLivrosVendidos);
		System.out.println("\nLista de livros mais vendidos --------");
		int iLV = 0;
		while (rLV.next()) {
			iLV++;
			System.out.println(iLV + "º - " + rLV.getString("l.titulo"));
			System.out.println("ID do Livro: " + rLV.getInt("l.idlivro"));
			System.out.println("Total Vendido: " + rLV.getInt("sum(iv.qtd)"));
			System.out.println("");
		}
		
		String sqlMaiorVenda = "SELECT c.idcliente, c.nome, v.total FROM clientes c\r\n" + 
				"INNER JOIN vendas v\r\n" + 
				"ON c.idcliente = v.idcliente \r\n" + 
				"ORDER BY v.total DESC;";
		Statement sMV = conn.createStatement();
		ResultSet rMV = sMV.executeQuery(sqlMaiorVenda);
		System.out.println("\nLista de Maiores Vendas --------");
		int iMV = 0;
		while (rMV.next()) {
			iMV++;
			System.out.println(iMV + "º - " + rMV.getString("c.nome"));
			System.out.println("ID do Cliente: " + rMV.getInt("c.idcliente"));
			System.out.println("Valor Total: " + rMV.getFloat("v.total"));
			System.out.println("");
		}
		
		String sqlMaisCompra = "SELECT c.idcliente, c.nome, sum(iv.qtd) AS compras FROM clientes c\r\n" + 
				"JOIN vendas v\r\n" + 
				"ON c.idcliente = v.idcliente\r\n" + 
				"JOIN itens_de_vendas iv \r\n" + 
				"ON v.idvenda = iv.idvenda\r\n" + 
				"GROUP BY c.idcliente, c.nome\r\n" + 
				"ORDER BY total DESC;";
		Statement sMC = conn.createStatement();
		ResultSet rMC = sMC.executeQuery(sqlMaisCompra);
		
		System.out.println("\nLista de Maiores Compras --------");
		int iMC = 0;
		while (rMC.next()) {
			iMC++;
			System.out.println(iMC + "º - " + rMC.getString("c.nome"));
			System.out.println("ID do Cliente: " + rMC.getInt("c.idcliente"));
			System.out.println("Unidades Compradas: " + rMC.getInt("compras"));
			System.out.println("");
		}
	}

	private static void delete(Venda venda, Livro livro) throws SQLException {
		System.out.print("Deseja mesmo deletar este item de venda? (S/n) ");
		String choice = scanner.nextLine();

		if (choice.equalsIgnoreCase("S")) {
			ItemVenda itemVenda = getItemVenda(livro, venda);
			int qtd = - itemVenda.getQtd();
			float subtotal = -itemVenda.getSubtotal();
			alterLivro(livro, qtd);
			
			String sql = "DELETE FROM itens_de_vendas WHERE idvenda = ? AND idlivro = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, venda.getIdvenda());
			ps.setInt(2, livro.getIdlivro());
			ps.execute();
			alterVendas(venda, subtotal);
			
			System.out.println("Item deletado com sucesso.");
		} else if (choice.equalsIgnoreCase("n")) {
			System.out.println("Operação cancelada.");
		} else {
			System.out.println("Opção não encontrada.");
			delete(venda, livro);
		}
	}

			private static void alter(Venda venda, Livro livro) throws SQLException {

			ItemVenda itemVenda = getItemVenda(livro, venda);
		System.out.print("1- Livro\n" + "2- Quantidade" + "\nEscolha o que deseja alterar (1/2): ");
		String opcao = scanner.nextLine();

		switch (opcao) {
		case "1": {
			System.out.print("Digite o livro desejado: ");
			String novoTitulo = scanner.nextLine();
			System.out.print("Digite o nome do autor: ");
			String novoNomeAutor = scanner.nextLine();
			Livro novoLivro = getLivro(novoTitulo, novoNomeAutor);
			int qtd = itemVenda.getQtd();
			int revertQtd = -itemVenda.getQtd();
			float initialSubtotal = -itemVenda.getSubtotal(); // Remover subtotal antigo.
			alterVendas(venda, initialSubtotal);
			
			alterLivro(livro, revertQtd);
			alterLivro(novoLivro, qtd);
			String sql = "UPDATE itens_de_vendas " + "SET idlivro = ? AND subtotal = ? "
					+ "WHERE idvenda = ? AND idlivro = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, novoLivro.getIdlivro());
			float subtotal = novoLivro.getPreco() * qtd;
			ps.setFloat(2, subtotal);
			ps.setInt(3, venda.getIdvenda());
			ps.setInt(4, livro.getIdlivro());
			ps.execute();
			alterVendas(venda, subtotal); // Adicionar novo subtotal.
			break;
		}
		case "2": {
			System.out.print("Digite a quantidade desejada: ");
			int novaQtd = scanner.nextInt(); scanner.nextLine();
			String sqlLivro = "SELECT estoque FROM livros WHERE idlivro = ?";
			PreparedStatement psLivro = conn.prepareStatement(sqlLivro);
			psLivro.setInt(1, livro.getIdlivro());
			ResultSet rL = psLivro.executeQuery();
			int estoque = 0;
			while (rL.next()) {
				estoque = rL.getInt("estoque");
			}
			String sqlItensVendas = "SELECT qtd FROM itens_de_vendas WHERE idvenda = ? AND idlivro = ?";
			PreparedStatement psItensVendas = conn.prepareStatement(sqlItensVendas);
			psItensVendas.setInt(1, venda.getIdvenda());
			psItensVendas.setInt(2, livro.getIdlivro());
			ResultSet rIV = psItensVendas.executeQuery();
			int qtd = 0;

			while (rIV.next()) {
				qtd = rIV.getInt("qtd");
			}

			int estoqueOriginal = estoque + qtd;
			int novoEstoque = estoqueOriginal - novaQtd;

			String sqlEstoque = "UPDATE livros SET estoque = ? WHERE idlivro = ?";
			PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque);
			psEstoque.setInt(1, novoEstoque);
			psEstoque.setInt(2, livro.getIdlivro());
			
			float initialSubtotal = -itemVenda.getSubtotal(); // Remover subtotal antigo.
			alterVendas(venda, initialSubtotal);

			String sql = "UPDATE itens_de_vendas " + "SET qtd = ? AND subtotal = ? "
					+ "WHERE idvenda = ? AND idlivro = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, novaQtd);
			float subtotal = livro.getPreco() * novaQtd;
			ps.setFloat(2, subtotal);
			ps.setInt(3, venda.getIdvenda());
			ps.setInt(4, livro.getIdlivro());
			ps.execute();
			alterVendas(venda, subtotal); // Adicionar novo subtotal.

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcao);
		}

	}

	private static void read(Venda venda, Livro livro) throws SQLException {

		String sql = "SELECT * FROM itens_de_vendas WHERE idvenda = ? AND idlivro = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, venda.getIdvenda());
		ps.setInt(2, livro.getIdlivro());
		ResultSet r = ps.executeQuery();
		while (r.next()) {
			System.out.println("-------------------------------------");
			System.out.println("idvenda: " + r.getInt("idvenda") + "\n" + "idlivro: " + r.getInt("idlivro") + "\n"
					+ "qtd: " + r.getInt("qtd") + "\n" + "subtotal: " + r.getFloat("subtotal"));
		}
	}
	private static void insert(Venda venda, Livro livro) throws SQLException {

		System.out.print("Insira a quantidade que deseja comprar: ");
		int qtd = scanner.nextInt(); scanner.nextLine();
		alterLivro(livro, qtd);

		String sql = "INSERT INTO itens_de_vendas (idvenda, idlivro, qtd, " + "subtotal) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, venda.getIdvenda());
		ps.setInt(2, livro.getIdlivro());
		ps.setInt(3, qtd);
		float subtotal = qtd * livro.getPreco();
		ps.setFloat(4, subtotal);
		ps.execute();
		alterVendas(venda, subtotal);
	}
	private static Livro getLivro(String titulo, String nomeAutor) throws SQLException {

		List<Livro> livros = new ArrayList<Livro>();
		List<Autor> autores = new ArrayList<Autor>();

		String sqlLivros = "SELECT * FROM livros WHERE titulo = ?";
		PreparedStatement psLivros = conn.prepareStatement(sqlLivros);
		psLivros.setString(1, titulo);
		ResultSet rLivros = psLivros.executeQuery();

		String sqlAutores = "SELECT * FROM autores WHERE nome = ?";
		PreparedStatement psAutores = conn.prepareStatement(sqlAutores);
		psAutores.setString(1, nomeAutor);
		ResultSet rAutores = psAutores.executeQuery();

		while (rLivros.next()) {
			Livro livro = new Livro(rLivros.getInt("idlivro"), rLivros.getString("titulo"), rLivros.getFloat("preco"),
					rLivros.getInt("estoque"));
			livros.add(livro);
		}

		while (rAutores.next()) {
			Autor autor = new Autor(rAutores.getInt("idautor"), rAutores.getString("nome"));
			autores.add(autor);
		}

		String sqlEscreve = "SELECT * FROM escreve WHERE idlivro = ? AND idautor = ?";
	PreparedStatement psEscreve = conn.prepareStatement(sqlEscreve);

		ResultSet rLivro = null;
		for (Autor a : autores) {
			for (Livro l : livros) {
				psEscreve.setInt(1, l.getIdlivro());
				psEscreve.setInt(2, a.getIdautor());
				ResultSet rEscreve = psEscreve.executeQuery();
				while (rEscreve.next()) {
					Escreve escreve = new Escreve(rEscreve.getInt("idlivro"), rEscreve.getInt("idautor"));
					if (escreve != null) {
						String sqlLivro = "SELECT * FROM livros WHERE idlivro = ?";
						PreparedStatement psLivro = conn.prepareStatement(sqlLivro);
						psLivro.setInt(1, escreve.getIdlivro());
						rLivro = psLivro.executeQuery();
						break;
					}
				}
			}
		}

		while (rLivro.next()) {
			Livro livroEscolhido = new Livro(rLivro.getInt("idlivro"), rLivro.getString("titulo"),
					rLivro.getFloat("preco"), rLivro.getInt("estoque"));
			return livroEscolhido;
		}

		return null;

	}

	private static Venda getVenda(int idvenda, Cliente cliente) throws SQLException {

		String sqlVendas = "SELECT * FROM vendas WHERE idcliente = ? AND idvenda = ?";
		
		PreparedStatement psVendas = conn.prepareStatement(sqlVendas);

		psVendas.setInt(1, cliente.getIdcliente());
		psVendas.setInt(2, idvenda);
		ResultSet rVendas = psVendas.executeQuery();
		float total = 0;
		while (rVendas.next()) {
			total = rVendas.getFloat("total");
		}

		Venda venda = new Venda(idvenda, cliente.getIdcliente(), total);
		return venda;

	}

	private static ItemVenda getItemVenda(Livro livro, Venda venda) throws SQLException {
		String sql = "SELECT * FROM itens_de_vendas WHERE idvenda = ? AND idlivro = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, venda.getIdvenda());
		ps.setInt(2, livro.getIdlivro());
		ResultSet r = ps.executeQuery();

		ItemVenda itemVenda = null;

		while (r.next()) {
			itemVenda = new ItemVenda(venda.getIdvenda(), livro.getIdlivro(), r.getInt("qtd"), r.getFloat("subtotal"));
		}

		return itemVenda;
}

	private static Cliente getCliente(String nome, String telefone) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE nome = ? AND telefone = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nome);
		ps.setString(2, telefone);
		ResultSet r = ps.executeQuery();
		
		int idcliente = 0;

		while (r.next()) {
			idcliente = r.getInt("idcliente");
		}
		
		Cliente cliente = new Cliente(idcliente, nome, telefone);
		return cliente;
	}
	
	private static void alterLivro(Livro livro, int qtd) throws SQLException {
		
		System.out.println("ID do Livro: " + livro.getIdlivro());
		System.out.println("Quantidade: " + qtd);
		int estoque = livro.getEstoque() - qtd;
		System.out.println("Estoque final: " + estoque);
		if (estoque < 0) {
			System.out.println("Valor superior ao estoque. Redirecionandon para o painel de controle...");
			System.out.println("Passou por aqui.");
			controlPanel();
		} else {
			String sql = "UPDATE livros SET estoque = ? WHERE idlivro = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, estoque);
			ps.setInt(2, livro.getIdlivro());
			ps.execute();
		}
	}
	private static void alterVendas(Venda venda, float subtotal) throws SQLException {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new Date();
		String dataNova = df.format(data);
		float total = venda.getTotal() + subtotal;
		String sql = "UPDATE vendas SET data = ?, total = ? WHERE idvenda = ? AND idcliente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, dataNova);
		ps.setFloat(2, total);
		ps.setInt(3, venda.getIdvenda());
		ps.setInt(4, venda.getIdcliente());
		ps.execute();
	}
	private static void findItemVenda(String option) throws SQLException {

		System.out.print("Informe o título do livro: ");
		String titulo = scanner.nextLine();
		System.out.print("Informe o nome do autor: ");
		String nomeAutor = scanner.nextLine();
		System.out.print("Informe o nome do cliente: ");
		String nomeCliente = scanner.nextLine();
		System.out.print("Informe o telefone do cliente: ");
		String telefone = scanner.nextLine();
		Cliente cliente = getCliente(nomeCliente, telefone);

		String sqlClienteVendas = "SELECT c.nome, v.idvenda FROM clientes c\r\n" + 
				"INNER JOIN vendas v\r\n" + 
				"ON c.idcliente = v.idcliente\r\n" + 
				"WHERE c.idcliente = ?";
		PreparedStatement psCV = conn.prepareStatement(sqlClienteVendas);
		psCV.setInt(1, cliente.getIdcliente());
		ResultSet rCV = psCV.executeQuery();
		System.out.println("Lista de ID de Vendas disponíveis para " + nomeCliente + ": ");
		while (rCV.next()) {
			System.out.println("ID: " + rCV.getInt("idvenda"));
		}
		
		System.out.print("Informe o ID da Venda: ");
		int idvenda = scanner.nextInt(); scanner.nextLine();
		Venda venda = getVenda(idvenda, cliente);
		Livro livro = getLivro(titulo, nomeAutor);
		switch (option) {
		case "insert": {
			insert(venda, livro);
			break;
		}
		case "read": {
			read(venda, livro);
			break;
		}
		case "alter": {
			alter(venda, livro);
			break;
		}
		case "delete": {
			delete(venda, livro);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
	}
 */

