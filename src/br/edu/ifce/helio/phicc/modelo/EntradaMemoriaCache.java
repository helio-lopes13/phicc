package br.edu.ifce.helio.phicc.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EntradaMemoriaCache {

	private static Integer quantidadeErros;

	private String[][] conteudo;

	private boolean erro = false;

	private Integer contadorAcessos = 1;

	private Integer contadorCache = 1;

	public static Integer getQuantidadeErros() {
		return quantidadeErros;
	}

	public static void setQuantidadeErros(Integer quantidadeErros) {
		EntradaMemoriaCache.quantidadeErros = quantidadeErros;
	}

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

	public void inserirErro() {
		Random random = new Random();
		int linha = 0, coluna = 0, i;

		List<int[]> posicoes = null;

		do {
			linha = random.nextInt(conteudo.length);
			coluna = random.nextInt(conteudo[0].length);
			posicoes = extrairVizinhos(linha, coluna);
		} while (quantidadeErros > posicoes.size() + 1);

		System.out.println("Bit de erro: [" + linha + "][" + coluna + "]");
		System.out.println("Número de erros: " + quantidadeErros);
		if (linha == 0 || linha == conteudo.length - 1 || coluna == 0 || coluna == conteudo[0].length - 1) {
			if (linha == 0) {
				if (coluna == 0) {
					System.out.println("Bit no canto esquerdo superior");
				} else if (coluna == conteudo[0].length - 1) {
					System.out.println("Bit no canto direito superior");
				} else {
					System.out.println("Bit na borda superior");
				}
			} else if (linha == conteudo.length - 1) {
				if (coluna == 0) {
					System.out.println("Bit no canto esquerdo inferior");
				} else if (coluna == conteudo[0].length - 1) {
					System.out.println("Bit no canto direito inferior");
				} else {
					System.out.println("Bit na borda inferior");
				}
			} else if (coluna == 0) {
				System.out.println("Bit na borda lateral esquerda");
			} else if (coluna == conteudo[0].length - 1) {
				System.out.println("Bit na borda lateral direita");
			}
		} else {
			System.out.println("Bit dentro da borda");
		}

		inverteBit(linha, coluna);
		for (i = 1; i <= quantidadeErros - 1; i++) {
			int[] posicao = posicoes.remove(random.nextInt(posicoes.size()));
			inverteBit(posicao);

			System.out.println("Removida posição [" + posicao[0] + "][" + posicao[1] + "]");
			posicoes.forEach((int[] novaPosicao) -> {
				System.out.println("Vizinho [" + novaPosicao[0] + "][" + novaPosicao[1] + "]");
			});
		}
	}

	private List<int[]> extrairVizinhos(int linha, int coluna) {
		List<int[]> posicoes = new ArrayList<>();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != i) {
					if (linha + i >= 0 && linha + i < conteudo.length && coluna + j >= 0
							&& coluna + j < conteudo[0].length) {
						int[] posicao = { linha + i, coluna + j };
						posicoes.add(posicao);
					}
				}
			}
		}

		return posicoes;
	}

	private void inverteBit(int... posicao) {
		conteudo[posicao[0]][posicao[1]] = String.valueOf(Integer.parseInt(conteudo[posicao[0]][posicao[1]]) ^ 1);
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
