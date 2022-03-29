package br.edu.ifce.helio.phicc.modelo;

import java.util.Arrays;

public class EntradaMemoriaCache {

	private String[][] conteudo;

	private boolean erro = false;

	private Integer contadorAcessos = 1;

	private Integer contadorCache = 1;

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

	public Integer getContadorAcessos() {
		return contadorAcessos;
	}

	public void setContadorAcessos(Integer contadorAcessos) {
		this.contadorAcessos = contadorAcessos;
	}

	public Integer getContadorCache() {
		return contadorCache;
	}

	public void setContadorCache(Integer contadorCache) {
		this.contadorCache = contadorCache;
	}
	
	public void incrementarContadorAcesso() {
		contadorAcessos++;
	}

	public void incrementarContadorCache() {
		contadorCache++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(conteudo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntradaMemoriaCache other = (EntradaMemoriaCache) obj;
		if (!Arrays.deepEquals(conteudo, other.conteudo))
			return false;
		return true;
	}

}
