package tabela;

import model.identificavel.User;

public interface THashMiniProjeto {

	/**
	 * calcula o hash da chave inteira id, utilizando o método da divisão
	 * @param id chave identificadora do objeto
	 * @return o índice mapeado
	 */
	public int hash(int id);
	
	/**
	 * método para averiguar se há espaços vazios na tabela (a tabela pode conter um atributo para contar a quantidade de elementos)
	 * @return verdadeiro se não houver espaços onde possam ser inseridos novos elementos
	 */
	public boolean isCheia();
	
	/**
	 * adiciona o usuário passado por parâmetro da tabela hash
	 * @param u usuário que será salvo na tabela
	 * @throws Exception lança uma exceção quando não houver espaços disponíveis
	 */
	public void adicionar(User u) throws Exception;

	/**
	 * o método retorna o usuário com id igual ao recebido como parâmetro de entrada
	 * @param id id do usuário que se deseja recuperar
	 * @return retorna o objeto usuário com o id igual ao recebido como parâmetro
	 * @throws Exception lança uma exceção caso o usuário nao seja encontrado
	 */
	public User recuperar(int id) throws Exception;
	
	/**
	 * esse método deve imprimir os índices da tabela hash
	 * ex. 
	 * [0] - vazio
	 * [1] - João (ID 1) (para tabelas com endereçamento aberto)
	 * [2] - INICIO -> Ana (ID 2) -> Maria (ID 4) (para tabelas com endereçamento fechado).
	 * [3] - RAIZ -> José (ID 3), Jonathas (ID 6) (percurso préordem para árvores). 
	 */
	public void print();
	
	/**
	 * função que dobra o tamanho da tabela hash e realoca todos os elementos existentes em seus novos índices
	 */
	public void crescer();
	
	/**
	 * @return retorna a quantidade de elementos guardada atualmente na tabela
	 */
	public int qtd();
	
}
