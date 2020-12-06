package tabela.arvore;

import model.identificavel.User;

public class TreeNode {
	
	private User dado;
	private TreeNode esq;
	private TreeNode dir;
	
	public TreeNode(User dado) {
		this.dado = dado;
	}
	
	public User getDado() {
		return dado;
	}
	public void setDado(User dado) {
		this.dado = dado;
	}
	public TreeNode getEsq() {
		return esq;
	}
	public void setEsq(TreeNode esq) {
		this.esq = esq;
	}
	public TreeNode getDir() {
		return dir;
	}
	public void setDir(TreeNode dir) {
		this.dir = dir;
	}
}
