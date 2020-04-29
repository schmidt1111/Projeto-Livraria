package com.qintess.application;
import java.util.List;
import java.util.Scanner;

import com.qintess.model.*;
import com.qintess.model.dao.*;

public class Application {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int op=1;
		while(op>0) {
			menu();
			op = sc.nextInt();
			if(op == 0) {
				sc.close();
				break;
			}
			operacao(op);
		}
	}
	public static void menu() {
		System.out.println("Digite a opção desejada:   ");
		System.out.println("1) Inserir dados na tabela Itens da Venda");
		System.out.println("2) Deletar dados na tabela Itens da Venda");
		System.out.println("3) Selecionar tabela Itens da Venda");
		System.out.println("4) Buscar dados pelo idvenda na tabela Itens da Venda");
		System.out.println("5) Relatório com o livro mais vendido");
		System.out.println("6) Relatório com a maior venda");
		System.out.println("7) Relatório com os clientes que realizaram os maiores numeros de compras");
		System.out.println("9) Criar tabelar (apenas a primeira vez)");
		System.out.println("0) Sair");
		
		}
	public static void operacao(int op) {
		switch (op) {
		case 1:
			inserir();
			break;
		case 2:
			deletar();
			break;
		case 3:
			System.out.println("IDVENDA   | IDLIVRO   | QUANTIDADE   | SUBTOTAL ");
			for(ItemDaVenda itemdavenda: ItemDaVendaDao.selectAll()) {
				System.out.println("IDVENDA:"+ itemdavenda.getIdvenda()+ "| IDLIVRO: " + itemdavenda.getIdlivro() +"| QUANTIDADE:"+ itemdavenda.getQtd()+" | SUBTOTAL: "+ itemdavenda.getSubtotal());
			}
			System.out.println("");
			break;
		case 4:
			buscarPorNome();
			break;
		case 5:
			System.out.println("5)--Relatório com o livro mais vendido--");
			relatorioLivroMaisVendido();
			
			break;
		case 6:
			System.out.println("6) Relatório com a maior venda.");
			relatorioMaiorVenda();
			
			break;
		case 7:
			System.out.println("7) Relatório com o cliente que realizou o maior numero de compras.");
			relatorioClienteMaisCompra();
			
			break;
		case 9:
			ClienteDao.createCienteDaoTable();
			System.out.println("\n\n\n");
			break;
			
		default:
			System.out.println("Essa operação não existe.");
			break;
		}
	}
	public static void inserir() {
		Scanner sc = new Scanner (System.in);
		System.out.println("Digite o idvenda para inserir na tabela Item Da Venda:");
		int idvenda = sc.nextInt();
		System.out.println("Digite o idlivro para inserir na tabela Item da Venda:");
		int idlivro = sc.nextInt();
		System.out.println("Digite a quantidade do  produto para inserir na tabela Item da Venda:");
		int qtd = sc.nextInt();
		System.out.println("Digite o valor do subtotal da compra para inserir na tabela Item da Venda:");
		double subtotal = sc.nextDouble();
		System.out.println("IDVENDA"+ idvenda+"IDLIVRO"+idlivro+"QUANTIDADE"+qtd+"SUBTOTAL"+subtotal);
		
		ItemDaVenda item = new ItemDaVenda(idvenda, idlivro, qtd, subtotal);
		ItemDaVendaDao.insert(item);
		System.out.println("Dados inseridos com sucesso na tabela Item da Venda.");
		System.out.println("");
	}
	public static void deletar() {
		Scanner sc = new Scanner(System.in);
		List<ItemDaVenda> lista = ItemDaVendaDao.selectAll();
		for(ItemDaVenda itemdavenda : lista) {
			System.out.println("IDVENDA: " + itemdavenda.getIdvenda() +"| "+ itemdavenda.getIdlivro());
		}
		System.out.println("Digite o Idvenda que deseja excluir");

		boolean verificar = false;
		boolean verificarId = false;

		while(!verificar) {
			try {
				int id = Integer.parseInt(sc.nextLine());

				for(ItemDaVenda itemdavenda : lista) {
					if(itemdavenda.getIdvenda() == id) {

						ItemDaVendaDao.delete(itemdavenda.getIdvenda());

						verificarId = true;
						verificar = true;

						System.out.println(itemdavenda.getIdvenda() + " Dados deletado com sucesso.");
						System.out.println("");
						break;
					}
				}
				if(!verificarId) {
					new Exception();
				}

			}catch (Exception e) {
				System.out.println("Digite um id válido");
			}
		}
	}
	public static void buscarPorNome() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite o idvenda que deseja buscar");
		int idvenda = sc.nextInt();
		
		for(ItemDaVenda itemdavenda : ItemDaVendaDao.selectByIdVenda(idvenda)) {
			System.out.println("IDVENDA: " + itemdavenda.getIdvenda() + " |IDLIVRO: " + itemdavenda.getIdlivro()+ "|QUANTIDADE: "+ itemdavenda.getQtd()+"|SUBTOTAL: "+itemdavenda.getSubtotal());
		}
		System.out.println("");
	}
	public static void relatorioLivroMaisVendido() {
		ItemDaVenda resul = ItemDaVendaDao.selectTop1();
		
		Livro res = LivroDao.selectByIdlivro(resul.getIdlivro());
		System.out.println("IDLIVRO: "+ resul.getIdlivro()+"| QUANTIDADE: "+resul.getQtd()+"|TITULO:"+res.getTitulo());
		System.out.println("");
		
	}
	public static void relatorioMaiorVenda() {
		Venda resultop2 = VendaDao.selectTop2();
		
		Cliente resu = ClienteDao.selectIdCliente(resultop2.getIdcliente());
		System.out.println("IDCLIENTE: "+ resultop2.getIdcliente()+ "|TOTAL: "+ resultop2.getTotal()+"|NOME: "+resu.getNome());
		System.out.println("");
	}
	public static void relatorioClienteMaisCompra() {
		List<Venda> result = VendaDao.selectTop3();
			for (Venda venda : result) {
				Cliente resu = ClienteDao.selectIdCliente(venda.getIdcliente());
				System.out.println("| IDCLIENTE: "+ venda.getIdcliente()+"| QTDIDCLIENTE: "+venda.getQtdidcliente()+ "| NOME: "+resu.getNome());	
				System.out.println("");
			}
			
		 
		}
}
