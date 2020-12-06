package tabela;

import java.util.ArrayList;

import model.identificavel.User;
import tabela.arvore.ABB;

public class TabelaHash implements THashMiniProjeto {

	private ABB[] arvores;
	
	public TabelaHash(int size) {
		arvores = new ABB[size];
	}

	// Método implementado
	@Override
	public int hash(int id) {
		return id % arvores.length;
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
		
		if(arvores[hash] == null)
			arvores[hash] = new ABB();
		
		arvores[hash].inserir(u);
	}

	// Método implementado
	@Override
	public User recuperar(int id) throws Exception {
		int hash = hash(id);
		
		if(arvores[hash] != null)
			return arvores[hash].buscar(id);
		
		throw new Exception("[ERRO] User não encontrado");
	}

	// Método implementado
	@Override
	public void print() {
		for (int i = 0; i < arvores.length; i++) {
			if(arvores[i] != null) {
				try {
					System.out.print("[" + i + "] - RAIZ -> ");
					arvores[i].preOrdem();
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
		ABB[] novaTabela = new ABB[arvores.length * 2];
		
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < arvores.length; i++) {
			ABB abb = arvores[i];
			
			for (int j = 0; j < abb.getAllUsers().size(); j++) {
				users.add(abb.getAllUsers().get(i));
			}
		}
		
		arvores = novaTabela;
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
		int total = 0;
		for (int i = 0; i < arvores.length; i++) {
			total += arvores[i].quantidade();
		}
		
		return total;
	}
}
