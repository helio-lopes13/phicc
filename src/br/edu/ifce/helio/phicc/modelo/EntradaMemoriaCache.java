package br.edu.ifce.helio.phicc.modelo;

public class EntradaMemoriaCache {

	private String[][] conteudo;

	private boolean erro = false;

	public String[][] getConteudo() {
		return conteudo;
	}

	public void setConteudo(String[][] conteudo) {
		this.conteudo = conteudo;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

}
