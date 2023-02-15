package br.edu.ifce.helio.phicc.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;

public class NovaEntradaMemoriaCache {

	private EntradaCodificada entradaCodificada;

	private static Integer quantidadeErros;

	private boolean erro = false;

	private Integer contadorAcessos = 1;

	private Integer contadorCache = 1;

	private List<int[]> listaPosicoes = new ArrayList<>();

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

	public void printPosicoes() {
		System.out.println("Erro nas posições:");
		for (int[] posicao : listaPosicoes) {
			System.out.println("[" + posicao[0] + "][" + posicao[1] + "]");
		}
	}

	public void inserirErro() {
		entradaCodificada.inserirErro(quantidadeErros);
	}

}
