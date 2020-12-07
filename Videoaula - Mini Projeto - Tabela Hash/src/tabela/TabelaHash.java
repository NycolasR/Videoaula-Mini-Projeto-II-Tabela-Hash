package tabela;

import java.util.ArrayList;

import model.identificavel.User;
import tabela.arvore.ABB;

public class TabelaHash implements THashMiniProjeto {

	private ABB[] tabela;
	private int quantidadeUsers;
	
	public TabelaHash(int size) {
		tabela = new ABB[size];
	}

	// Método implementado
	@Override
	public int hash(int id) {
		return id % tabela.length;
	}

	// Método implementado
	@Override
	public boolean isCheia() {
		return false; // Endereçamento Fechado: nunca estará cheia
	}

	// Método implementado
	@Override
	public void adicionar(User u) throws Exception {
		int hash = hash(u.getId());
		
		if(tabela[hash] == null)
			tabela[hash] = new ABB();
		
		tabela[hash].inserir(u);
		quantidadeUsers++;
	}

	// Método implementado
	@Override
	public User recuperar(int id) throws Exception {
		int hash = hash(id);
		
		if(tabela[hash] != null)
			return tabela[hash].buscar(id);
		
		throw new Exception("[ERRO] User não encontrado");
	}

	// Método implementado
	@Override
	public void print() {
		for (int i = 0; i < tabela.length; i++) {
			if(tabela[i] != null) {
				try {
					System.out.print("[" + i + "] - RAIZ -> ");
					tabela[i].preOrdem();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println();
			}		
		}
	}

	// Método implementado
	@Override
	public void crescer() {
		ABB[] novaTabela = new ABB[tabela.length * 2];
		
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < tabela.length; i++) {
			ABB abb = tabela[i];
			
			if(abb != null) {
				ArrayList<User> usersDaArvore = abb.getAllUsers();
				
				for (int j = 0; j < usersDaArvore.size(); j++) {
					users.add(usersDaArvore.get(j));
				}				
			}
		}
		
		tabela = novaTabela;
		for (int i = 0; i < users.size(); i++) {
			try {
				adicionar(users.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Método implementado
	@Override
	public int qtd() {
//		int total = 0;
//		for (int i = 0; i < tabela.length; i++) {
//			if(tabela[i] != null)
//				total += tabela[i].quantidade();
//		}
//		
//		return total;
		
		return quantidadeUsers;
	}
}
