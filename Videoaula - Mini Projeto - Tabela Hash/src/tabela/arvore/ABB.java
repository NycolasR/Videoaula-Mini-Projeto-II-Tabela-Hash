package tabela.arvore;

import java.util.ArrayList;

import model.identificavel.User;

public class ABB {
	
	private TreeNode raiz;
	
	public TreeNode getRaiz() {
		return raiz;
	}
	
	public void inserir(User dado) throws Exception {
		if(isVazia()) {
			raiz = new TreeNode(dado);
		} else {
			inserirRecursivo(raiz, dado);
		}
	}
	
	/**
	 * @param raizSub Raiz da sub-árvore criada a cada chamada recursiva
	 * @param dado Valor inteiro a ser inserido
	 * @throws Exception Lançaa exceçãoo caso o valor já se encontre na árvore 
	 */
	private void inserirRecursivo(TreeNode raizSub, User dado) throws Exception {
		if(dado.getId() == raizSub.getDado().getId())
			
			throw new Exception("[ERRO] Valor duplicado"); // Encontrando valor igual -> nÃ£o permitido
			
		if(dado.getId() < raizSub.getDado().getId()) { // Se eh menor, vai para a esquerda
			if(raizSub.getEsq() == null) {
				raizSub.setEsq(new TreeNode(dado));
			} else {
				inserirRecursivo(raizSub.getEsq(), dado); // Caso a posiÃ§Ã£o jÃ¡ esteja ocupada, a busca pela posiÃ§Ã£o live continua a partir desta
			}
		}
		
		if(dado.getId() > raizSub.getDado().getId()) { // Se eh maior, vai para a direita
			if(raizSub.getDir() == null) {
				raizSub.setDir(new TreeNode(dado));
			} else {
				inserirRecursivo(raizSub.getDir(), dado); // Caso a posiÃ§Ã£o jÃ¡ esteja ocupada, a busca pela posiÃ§Ã£o live continua a partir desta
			}
		}
	}
	
	public void inserirIterativo(User dado) throws Exception {
		if(isVazia()) {
			raiz = new TreeNode(dado);
		} else {
			TreeNode aux = raiz;
			
			while(true) { // Para encontrar uma posicao disponivel para insercao
				if(dado == aux.getDado()) { // Encontrando valor igual -> não permitido
					
					throw new Exception("[ERRO] Valor duplicado");
					
				} else if(dado.getId() < aux.getDado().getId()) { // Se eh menor, vai para a esquerda
					
					if(aux.getEsq() == null) { // Se a posicao estiver null
						aux.setEsq(new TreeNode(dado)); // Setando o valor
						return;
					} else {
						aux = aux.getEsq(); // Caso a posiÃ§Ã£o jÃ¡ esteja ocupada, a busca pela posição live continua a partir desta
					}
					
				} else if(dado.getId() > aux.getDado().getId()) { // Se eh maior, vai para a direita
					
					if(aux.getDir() == null) { // Se a posicao estiver null
						aux.setDir(new TreeNode(dado)); // Setando o valor
						return;
					} else {
						aux = aux.getDir(); // Caso a posiÃ§Ã£o jÃ¡ esteja ocupada, a busca pela posição live continua a partir desta
					}
				}
			}
		}
	}
	
	public User maior() throws Exception {
		if(isVazia())
			throw new Exception("[ERRO] Ã?rvore vazia");
		
		return maior(raiz);
		
//		Versão Iterativa
//		TreeNode aux = raiz;
//		while(aux.getDir() != null)
//			aux = aux.getDir();
//		
//		return aux.getDir().getDado();
	}
	
	private User maior(TreeNode raizSub) throws Exception {
		if(raizSub == null)
			throw new Exception("[ERRO] NÃ£o hÃ¡ elementos nesta Ã¡rvore");
		
		if(raizSub.getDir() == null)
			return raizSub.getDado();
		else
			return maior(raizSub.getDir());
	}

 	public User menor() throws Exception {
		if(isVazia())
			throw new Exception("[ERRO] Ã?rvore vazia");
		
		return menor(raiz);
		
//		Versãoo iterativa
//		TreeNode aux = raiz;
//		while(aux.getEsq() != null)
//			aux = aux.getEsq();
//		
//		return aux.getEsq().getDado();
	}
	
	private User menor(TreeNode raizSub) throws Exception {
		if(raizSub == null)
			throw new Exception("[ERRO] NÃ£o hÃ¡ elementos nesta Ã¡rvore");
		
		if(raizSub.getEsq() == null)
			return raizSub.getDado();
		else
			return menor(raizSub.getEsq());
	}

	public User remover(User dado) throws Exception {
		if(isVazia()) {
			throw new Exception("[ERRO] Esta árvore está vazia");
		} else if(raiz.getDado().getId() == dado.getId() && raiz.getDir() == raiz.getEsq()) { // Remoção da raiz sem nenhum filho
			User lixo = raiz.getDado();
			raiz = null;
			return lixo;
		} else {
			return remover(null, raiz, dado);
		}
	}
	
	private User remover(TreeNode paiRaizSub, TreeNode raizSub, User dado) throws Exception {
		if(raizSub == null) {
			
			throw new Exception("[ERRO] Elemento nÃ£o encontrado");
			
		} else if(dado.getId() == raizSub.getDado().getId()) { // Caso onde o elemento a ser removido Ã© encontrado
			if(raizSub.getEsq() == raizSub.getDir()) { // O node a ser removido Ã© uma folha
				
				if(raizSub == paiRaizSub.getEsq())
					paiRaizSub.setEsq(null);
				else // if(raizSub == paiRaizSub.getDir())
					paiRaizSub.setDir(null);
				
				return raizSub.getDado();
				
			} else { // O node a ser removido nÃ£o Ã© uma folha (tem pelo menos um dos filhos)
				
				User lixo = raizSub.getDado(); 
				try {
					User maiorEsquerda = maior(raizSub.getEsq()); // Obtendo o maior valor da esquerda
					raizSub.setDado(maiorEsquerda); // Substituindo o valor removido pelo maior da esquerda
					remover(raizSub, raizSub.getEsq(), maiorEsquerda); // Removendo elemento que se tornou duplicado
				} catch(Exception e) {
					User menorDireita = menor(raizSub.getDir()); // Obtendo o menor valor da direita
					raizSub.setDado(menorDireita); // Substituindo o valor removido pelo menor da direita
					remover(raizSub, raizSub.getDir(), menorDireita); // Removendo elemento que se tornou duplicado
				}
				
				return lixo;
			}
			
			
		} else if(dado.getId() < raizSub.getDado().getId()) { // Elemento a ser removido estÃ¡ a esqueda
			return remover(raizSub, raizSub.getEsq(), dado);
		} else /* if(dado > raizSub.getDado()) */ { // Elemento a ser removido estÃ¡ a direita
			return remover(raizSub, raizSub.getDir(), dado);
		}
	}
	
	public User buscar(int id) throws Exception {
		if(isVazia())
			throw new Exception("[ERRO] Ã?rvore vazia");
		
		return buscar(raiz, id);
	}
	
	private User buscar(TreeNode raizSub, int id) throws Exception {
		if(raizSub == null) 
			throw new Exception("[ERRO] Valor nÃ£o encontrado.");
		else if(id == raizSub.getDado().getId())
			return raizSub.getDado();
		else if(id < raizSub.getDado().getId())
			return buscar(raizSub.getEsq(), id);
		else // if(dado > raizSub.getDado())
			return buscar(raizSub.getDir(), id);
	}
	
	public void preOrdem() throws Exception {
		preOrdem(raiz);
	}
	
	public void preOrdem(TreeNode raizSub) throws Exception {
		if(isVazia())
			throw new Exception("[ERRO] Esta árvore está vazia");
		
		System.out.print(raizSub.getDado() + ", ");
		if(raizSub.getEsq() != null)
			preOrdem(raizSub.getEsq());
		if(raizSub.getDir() != null)
			preOrdem(raizSub.getDir());
	}
	
	public void inOrdem() throws Exception {
		inOrdem(raiz);
	}
	
	public void inOrdem(TreeNode raizSub) throws Exception {
		if(isVazia())
			throw new Exception("[ERRO] Esta árvore está vazia");
		
		if(raizSub.getEsq() != null)
			inOrdem(raizSub.getEsq());
		System.out.print(raizSub.getDado() + ", ");
		if(raizSub.getDir() != null)
			inOrdem(raizSub.getDir());
	}
	
	public void posOrdem() throws Exception {
		posOrdem(raiz);
	}

	public void posOrdem(TreeNode raizSub) throws Exception {
		if(isVazia())
			throw new Exception("[ERRO] Esta árvore está vazia");
		
		if(raizSub.getEsq() != null)
			posOrdem(raizSub.getEsq());
		if(raizSub.getDir() != null)
			posOrdem(raizSub.getDir());
		System.out.print(raizSub.getDado() + ", ");
	}

	public boolean isVazia() {
		return raiz == null;
	}
	
	public int quantidade() {
		return quantidade(raiz);
	}
	
	public int fatorBalanceamento(TreeNode treeNode) {
		return altura(treeNode.getDir()) - altura(treeNode.getEsq());
	}
	
	public int altura(TreeNode treeNode) {
		
		int alturaEsq = 0;
		int alturaDir = 0;
		
		if(treeNode == null) {
			return -1;
		} else {
			alturaEsq = altura(treeNode.getEsq());
			alturaDir = altura(treeNode.getDir());
			
			if(alturaEsq > alturaDir)
				return alturaEsq + 1;
			else
				return alturaDir + 1;
		}
	}
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		getAllUsers(raiz, users);
		return users;
	}
	
	private void getAllUsers(TreeNode raizSub, ArrayList<User> users) {
		if(raizSub != null)
			users.add(raizSub.getDado());
		
		if(raizSub.getEsq() != null) {
			getAllUsers(raizSub.getEsq(), users);
		}
		
		if(raizSub.getDir() != null) {
			getAllUsers(raizSub.getDir(), users);
		}
		
		return;
	}

	private int quantidade(TreeNode raizSub) {
		if(raizSub == null)
			return 0;
		else
			return quantidade(raizSub.getEsq()) + quantidade(raizSub.getDir()) + 1;
	}
}












