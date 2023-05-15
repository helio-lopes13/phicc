package br.edu.ifce.helio.phicc.implementacao;

public interface EntradaCodificada {

	Object getValor();

	boolean isErro();

	void setValor(Object valor);

	void inserirErro(Integer quantidadeErros);

	void printEntrada();
	
	void printPosicoes();

	boolean valorEquals(Object valor);

	boolean isValida();

}
