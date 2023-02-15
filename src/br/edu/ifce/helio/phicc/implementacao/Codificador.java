package br.edu.ifce.helio.phicc.implementacao;

public interface Codificador {
	public EntradaCodificada codificar(String palavra);
	
	public String decodificar(EntradaCodificada entrada);
}
