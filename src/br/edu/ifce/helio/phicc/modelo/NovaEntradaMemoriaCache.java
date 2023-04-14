package br.edu.ifce.helio.phicc.modelo;

import java.util.Objects;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;

public class NovaEntradaMemoriaCache {

	private EntradaCodificada entradaCodificada;

	private static Integer quantidadeErros;

	private Integer contadorAcessos = 1;

	private Integer contadorCache = 1;

	public static Integer getQuantidadeErros() {
		return quantidadeErros;
	}

	public static void setQuantidadeErros(Integer quantidadeErros) {
		NovaEntradaMemoriaCache.quantidadeErros = quantidadeErros;
	}

	public EntradaCodificada getEntradaCodificada() {
		return entradaCodificada;
	}

	public void setEntradaCodificada(EntradaCodificada entradaCodificada) {
		this.entradaCodificada = entradaCodificada;
	}

	public boolean isErro() {
		return entradaCodificada.isErro();
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

	public void printPosicoes() {
		entradaCodificada.printPosicoes();
	}

	public void inserirErro() {
		entradaCodificada.inserirErro(quantidadeErros);
	}

	@Override
	public int hashCode() {
		return Objects.hash(entradaCodificada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NovaEntradaMemoriaCache other = (NovaEntradaMemoriaCache) obj;
		return Objects.equals(entradaCodificada, other.entradaCodificada);
	}

}
