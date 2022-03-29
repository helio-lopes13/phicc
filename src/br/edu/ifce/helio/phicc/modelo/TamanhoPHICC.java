package br.edu.ifce.helio.phicc.modelo;

public enum TamanhoPHICC {
	T40(40),
	T44(44),
	T32(32),
	T36(36);
	
	private int tamanho;
	
	TamanhoPHICC(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public int getTamanho() {
		return tamanho;
	}
}
