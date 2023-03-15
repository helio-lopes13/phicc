package br.edu.ifce.helio.phicc.implementacao;

public interface EntradaCodificada {

	Object getValor();

	void setValor(Object valor);

	void inserirErro(Integer quantidadeErros);

	void printEntrada();

	boolean valorEquals(Object valor);

}
