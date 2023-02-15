package br.edu.ifce.helio.phicc.implementacao;

public class EntradaCodificadaLinear implements EntradaCodificada {
	private String valor = null;
	
	public EntradaCodificadaLinear(String valor) {
		setValor(valor);
	}

	public Object getValor() {
		if (valor != null) {
			return valor;
		} else {
			return null;
		}
	}
	
	public void setValor(Object valor) {
		if (valor instanceof String) {
			this.valor = (String) valor;
		} else {
			throw new RuntimeException("Tipo de valor inválido");
		}
	}
	
	public void inserirErro(Integer quantidadeErros) {
		if (valor != null) {
			inserirErroLinear();
		} else {
			throw new RuntimeException("Valor não pode estar vazio");
		}
	}

	public void printEntrada() {
		System.out.println(valor);
	}
	
	private void inserirErroLinear() {
//		Random random = new Random();
//		int posicao = 0, i;
	}

}
